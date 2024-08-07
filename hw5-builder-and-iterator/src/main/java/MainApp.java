import java.util.Iterator;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        List<Product> firstList = List.of(
                Product.builder().id("1").title("Product 1").build(),
                Product.builder().id("2").title("Product 2").build(),
                Product.builder().id("3").title("Product 3").build(),
                Product.builder().id("4").title("Product 4").build()
        );
        List<Product> secondList = List.of(
                Product.builder().id("5").title("Product 5").build(),
                Product.builder().id("6").title("Product 6").build(),
                Product.builder().id("7").title("Product 7").build(),
                Product.builder().id("8").title("Product 8").build()
        );
        List<Product> thirdList = List.of(
                Product.builder().id("9").title("Product 9").build(),
                Product.builder().id("10").title("Product 10").build(),
                Product.builder().id("11").title("Product 11").build(),
                Product.builder().id("12").title("Product 12").build()
        );
        List<Product> fourthList = List.of(
                Product.builder().id("13").title("Product 13").build(),
                Product.builder().id("14").title("Product 14").build()
        );

        Box box = new Box(firstList, secondList, thirdList, fourthList);
        for (Product product : box) {
            System.out.println(product);
        }
    }
}
