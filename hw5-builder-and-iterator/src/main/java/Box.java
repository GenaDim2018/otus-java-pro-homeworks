import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Box implements Iterable<Product> {
    //    List<Product> firstList;
//    List<Product> secondList;
//    List<Product> thirdList;
//    List<Product> fourthList;
    List<Product> products = new ArrayList<>();

    public Box(List<Product> firstList, List<Product> secondList, List<Product> thirdList, List<Product> fourthList) {
//        this.firstList = firstList;
//        this.secondList = secondList;
//        this.thirdList = thirdList;
//        this.fourthList = fourthList;
        products.addAll(firstList);
        products.addAll(secondList);
        products.addAll(thirdList);
        products.addAll(fourthList);
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }

}