package ru.otus.bank.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.bank.dao.AccountDao;
import ru.otus.bank.entity.Account;
import ru.otus.bank.entity.Agreement;
import ru.otus.bank.service.exception.AccountException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    AccountDao accountDao;

    @InjectMocks
    AccountServiceImpl accountServiceImpl;

    @Test
    public void testTransfer() {
        Account sourceAccount = new Account();
        sourceAccount.setAmount(new BigDecimal(100));

        Account destinationAccount = new Account();
        destinationAccount.setAmount(new BigDecimal(10));

        when(accountDao.findById(eq(1L))).thenReturn(Optional.of(sourceAccount));
        when(accountDao.findById(eq(2L))).thenReturn(Optional.of(destinationAccount));

        accountServiceImpl.makeTransfer(1L, 2L, new BigDecimal(10));

        assertEquals(new BigDecimal(90), sourceAccount.getAmount());
        assertEquals(new BigDecimal(20), destinationAccount.getAmount());
    }

    @Test
    public void testSourceNotFound() {
        when(accountDao.findById(any())).thenReturn(Optional.empty());

        AccountException result = assertThrows(AccountException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                accountServiceImpl.makeTransfer(1L, 2L, new BigDecimal(10));
            }
        });
        assertEquals("No source account", result.getLocalizedMessage());
    }


    @Test
    public void testTransferWithVerify() {
        Account sourceAccount = new Account();
        sourceAccount.setAmount(new BigDecimal(100));
        sourceAccount.setId(1L);

        Account destinationAccount = new Account();
        destinationAccount.setAmount(new BigDecimal(10));
        destinationAccount.setId(2L);

        when(accountDao.findById(eq(1L))).thenReturn(Optional.of(sourceAccount));
        when(accountDao.findById(eq(2L))).thenReturn(Optional.of(destinationAccount));

        ArgumentMatcher<Account> sourceMatcher =
                argument -> argument.getId().equals(1L) && argument.getAmount().equals(new BigDecimal(90));

        ArgumentMatcher<Account> destinationMatcher =
                argument -> argument.getId().equals(2L) && argument.getAmount().equals(new BigDecimal(20));

        accountServiceImpl.makeTransfer(1L, 2L, new BigDecimal(10));

        verify(accountDao).save(argThat(sourceMatcher));
        verify(accountDao).save(argThat(destinationMatcher));
    }

    @Test
    public void testAddAccount() {
        Account account = new Account();
        account.setId(1L);
        account.setAmount(new BigDecimal(100));
        account.setNumber("1");
        account.setType(1);
        Agreement agreement = new Agreement();
        agreement.setId(1L);
        account.setAgreementId(agreement.getId());

        ArgumentMatcher<Account> accountMatcher = argument -> argument != null &&
                argument.getAmount().equals(account.getAmount()) &&
                argument.getType().equals(account.getType()) &&
                argument.getNumber().equals(account.getNumber()) &&
                argument.getAgreementId().equals(account.getAgreementId());

        when(accountDao.save(Mockito.argThat(accountMatcher))).thenReturn(account);
        Account result = accountServiceImpl.addAccount(agreement, "1", 1, new BigDecimal(100));
        assertNotNull(result);
    }

    @Test
    public void getAccountsTest() {
        Iterable<Account> accounts = List.of(new Account(), new Account(), new Account());
        when(accountDao.findAll()).thenReturn(accounts);
        List<Account> result = accountServiceImpl.getAccounts();
        boolean flag = true;
        for (Account account : accounts) {
            if (result.stream().noneMatch(res -> res.equals(account))) flag = false;
        }
        assertTrue(flag);
    }

    @Test
    public void chargeTest() {
        Account account = new Account();
        account.setId(1L);
        account.setAmount(new BigDecimal(1000));
        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        when(accountDao.findById(1L)).thenReturn(Optional.of(account));
        when(accountDao.save(accountCaptor.capture())).thenReturn(account);
        assertTrue(accountServiceImpl.charge(1L, new BigDecimal(100)));
        assertEquals(new BigDecimal(900), accountCaptor.getValue().getAmount());
        AccountException accountException = assertThrows(AccountException.class, () ->
                accountServiceImpl.charge(2L, new BigDecimal(1000)));
        assertEquals("No source account", accountException.getLocalizedMessage());
    }
}
