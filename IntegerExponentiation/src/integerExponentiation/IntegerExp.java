 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package integerExponentiation;

import java.util.ArrayList;

/**
 *
 * @author Rahul Jain
 */
public class IntegerExp {

    private Integer x, n;
    private ArrayList<Integer> EXP = new ArrayList<Integer>(100);

    public Integer calcExp(Integer a, Integer b) {
    	if(a.equals(0)||a.equals(1)) {
    		return a;
    	}
        this.n = Math.abs(b);
        if(b < 0) {
            this.x = 1/a;
        }
        else this.x = a;
        EXP.add(1); //x^0
        EXP.add(x);
        EXP.add(x*x);
        EXP.add(x*x*x);
        EXP.add(x*x*x*x);
        EXP.add(x*x*x*x*x);
        /*EXP.add(x*x*x*x*x*x);
        EXP.add(x*x*x*x*x*x*x);
        EXP.add(x*x*x*x*x*x*x*x);*/ //x^8
        Integer result = new MemoisedRecursion().exp(this.n);
        return result;
    }
    
    private class MemoisedRecursion {
        private Integer result = new Integer(0);
        private Integer exp(Integer n) {
			if (n.intValue() < EXP.size()) 
				return EXP.get(n.intValue());
			return calculateAndStore(n);
		}
        private Integer calculateAndStore(Integer n) {
            if(n.intValue()%2 == 0) {
                //Integer tmp = exp(n/2);
                //result = tmp*tmp;
                result = exp(n/2)*exp(n/2); //x^(2n) = x^n * x^n
            }
            else {
                result = exp(n-1)*exp(1);   //x^(n+1) = x^n * x
            }
            EXP.add(result);    //add the result at proper index
            return result;
        }
	}
}
