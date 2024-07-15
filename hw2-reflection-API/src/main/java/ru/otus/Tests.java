package ru.otus;

import ru.otus.annotations.AfterSuite;
import ru.otus.annotations.BeforeSuite;
import ru.otus.annotations.Test;

public class Tests {
    @BeforeSuite
    public static void beforeTests(){
        System.out.println("1 Before tests");
    }
    @Test(priority = 2)
    public static void testPriority2(){
        System.out.println("2 Test with priority 2");
    }
    @Test(priority = 10)
    public static void testPriority10(){
        System.out.println("10 Test with priority 10");
    }
    @Test
    public static void testWithoutPriority(){
        System.out.println("2 Test without priority");
    }
    @Test
    public static void testWithoutPriority1(){
        System.out.println("2 Test without priority 1");
    }
    @Test
    public static void testWithoutPriority2(){
        System.out.println("2 Test without priority 2");
    }
    @Test
    public static void testWithoutPriority3(){
        System.out.println("2 Test without priority 3");
    }
    @Test(priority = 5)
    public static void testPriority5(){
        System.out.println("5 Test with priority 5");
    }
    @Test(priority = 6)
    public static void testPriority6(){
        System.out.println("6 Test with priority 6");
    }
    @Test(priority = 7)
    public static void testPriority7(){
        System.out.println("7 Test with priority 7");
    }
    @Test(priority = 8)
    public static void testPriority8(){
        System.out.println("8 Test with priority 8");
    }
    @Test(priority = 9)
    public static void testPriority9(){
        System.out.println("9 Test with priority 9");
    }

    @AfterSuite
    public static void afterTests(){
        System.out.println("11 After tests");
    }
}
