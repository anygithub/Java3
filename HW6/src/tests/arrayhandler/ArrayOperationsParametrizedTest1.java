package tests.arrayhandler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import main.arrayhandler.ArrayOperations;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class ArrayOperationsParametrizedTest1 {
	private ArrayOperations aop;
    private int[] array;
    private int[] result;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {new int[] {1, 2, 4, 4, 3, 4, 1, 7}, new int[]{1, 7}},
            {new int[] {1, 2, 4, 4, 2}, new int[]{2}},
            {new int[] {1, 2, 4}, new int[]{}},
            {new int[] {4}, new int[]{}},
        });
    }

    public ArrayOperationsParametrizedTest1(int[] array, int[] result) {
        this.array = array;
        this.result = result;
    }

    @Before
    public void init() {
    	aop = new ArrayOperations();
    }

    @Test
    public void inputArrayContains4() {
        Assert.assertArrayEquals(result, aop.getNumbersAfrerLastFour(array));
    }
}
