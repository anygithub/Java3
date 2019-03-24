import java.util.ArrayList;
import java.util.Arrays;

public class ArrayLinkType {
    //task1
    public  <T> void swapElements(int idx1, int idx2, T[] array) {
        T tmp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = tmp;
    }

    //task2
    public  <T> ArrayList<T> convertArrayToList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}
