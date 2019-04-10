package main.arrayhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayOperations {
	
	/* Task2
	 * Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив. 
	 * Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов, 
	 * идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку, иначе в методе необходимо 
	 * выбросить RuntimeException.
	 * Написать набор тестов для этого метода (по 3-4 варианта входных данных).
	 * Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].*/
    public int[] getNumbersAfrerLastFour(int[] array) throws RuntimeException {
        for (int i = array.length - 1; i >= 0; i--)
            if (array[i] == 4) {
                int idx = i + 1;
                int[] result = new int[array.length - idx];
                System.arraycopy(array, idx, result, 0, result.length);
                return result;
            }
        throw new RuntimeException("There are no numbers 4");
    }
	
    /* Task3
     * Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы, то метод вернет false; 
     * Написать набор тестов для этого метода (по 3-4 варианта входных данных).
     * [ 1 1 1 4 4 1 4 4 ] -> true
     * [ 1 1 1 1 1 1 ] -> false
     * [ 4 4 4 4 ] -> false
     * [ 1 4 4 1 1 4 3 ] -> false*/
    public boolean validateArrayByOneAndFour(int[] array) {
        boolean containsOne = false;
        boolean containsFour = false;
        for (int i : array)
            if (i == 1)
                containsOne = true;
            else if (i == 4)
                containsFour = true;
            else
                return false;
        return containsOne && containsFour;
    }
    
	
}
