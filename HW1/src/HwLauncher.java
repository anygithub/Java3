import task3.Apple;
import task3.Box;
import task3.Orange;

import java.util.Arrays;

public class HwLauncher {
    public static void main(String[] args) {
        ArrayLinkType arr = new ArrayLinkType();

        // tasks 1, 2

        String[] strings = {"First", "Second", "Third", "Fourth", "Five"};
        System.out.println(arr.convertArrayToList(strings));
        arr.swapElements(0, 2, strings);
        System.out.println(Arrays.toString(strings));

        Integer[] numbers = {1, 2, 3, 4, 5, 6};
        System.out.println(arr.convertArrayToList(numbers));
        arr.swapElements(0, 2, numbers);
        System.out.println(Arrays.toString(numbers));

        // task 3

        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        for (int i = 0; i < 7; i++) {
            appleBox.add(new Apple());
            orangeBox.add(new Orange());
        }
        System.out.println(appleBox.getFruits());
        System.out.println(appleBox.getWeight());

        System.out.println(orangeBox.getFruits());
        System.out.println(orangeBox.getWeight());

        System.out.println(appleBox.compareByWeight(orangeBox));

        Box<Apple> appleBox1 = new Box<>();
        appleBox1.add(new Apple());

        //failed add orange in apple box operation
        //appleBox1.add(new Orange());

        appleBox.moveAllFruitsToOtherBox(appleBox1);
        System.out.println(appleBox.getFruits());
        System.out.println(appleBox1.getFruits());

        //failed operation - move apples to orange box
        //appleBox1.moveAllFruitsToOtherBox(orangeBox);
    }
}
