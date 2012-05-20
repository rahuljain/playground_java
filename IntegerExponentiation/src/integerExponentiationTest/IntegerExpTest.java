/**
 * 
 */
package integerExponentiationTest;

import integerExponentiation.IntegerExp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rahul Jain
 */
public class IntegerExpTest {
    IntegerExp xToN;
    Integer x, n;

    @Before
    public void setUp() {
        xToN = new IntegerExp();
    }
    
    @After
    public void tearDown() {
    	xToN = null;
    	x = null;
    	n = null;
    }
    
    @Test
    public void Test0() {
        x = new Integer(5);
        n = new Integer(0);
        Integer expectResult = new Integer(1);
        Integer result = xToN.calcExp(x, n);
        assertEquals(expectResult, result);
    }
    
    @Test
    public void Test1() {
        x = new Integer(2);
        n = new Integer(10);
        Integer expectResult = new Integer(1024);
        Integer result = xToN.calcExp(x, n);
        assertEquals(expectResult, result);
    }
    
    @Test
    public void Test2() {
        x = new Integer(2);
        n = new Integer(2);
        Integer expectResult = new Integer(4);
        Integer result = xToN.calcExp(x, n);
        assertEquals(expectResult, result);
    }
    
    @Test
    public void Test3() {
        x = new Integer(3);
        n = new Integer(9);
        Integer expectResult = new Integer(19683);
        Integer result = xToN.calcExp(x, n);
        assertEquals(expectResult, result);
    }
    
    @Test
    public void Test4() {
        x = new Integer(2);
        n = new Integer(19);
        Integer expectResult = new Integer(524288);
        Integer result = xToN.calcExp(x, n);
        assertEquals(expectResult, result);
    }
    
    @Test
    public void Test5() {
        x = new Integer(2);
        n = new Integer(-3);
        Integer expectResult = new Integer(0);
        Integer result = xToN.calcExp(x, n);
        assertEquals(expectResult, result);
    }
}
