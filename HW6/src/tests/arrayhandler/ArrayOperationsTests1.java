package tests.arrayhandler;

import org.junit.Before;
import org.junit.Test;

import main.arrayhandler.ArrayOperations;

public class ArrayOperationsTests1 {
	private ArrayOperations aop;
    private int[] array={1,2,3,6,7}; 
    
    @Before
    public void init() {
    	aop = new ArrayOperations();
    }
    
    @Test (expected = RuntimeException.class)
    public void inputArrayContains4() {
        aop.getNumbersAfrerLastFour(array);
    }
}
