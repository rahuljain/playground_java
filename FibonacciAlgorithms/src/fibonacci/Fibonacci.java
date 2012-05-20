package fibonacci;

import java.math.BigInteger;
import static java.math.BigInteger.*;
import java.util.ArrayList;

public abstract class Fibonacci {

	int addCount;
	int multCount;
	long opDuration; // in microseconds
	

	public BigInteger apply(int n) {
		if (n < 0) throw new IllegalArgumentException("negative argument " + n);
		addCount = 0;
		long startTime = System.nanoTime();
		BigInteger result = fib(n);
		opDuration = (System.nanoTime() - startTime) / 1000;  // convert nano to micro
		return result;
	}
	
	public void display(int n) {
		BigInteger fib = apply(n);
		System.out.print(getClass().getName() + " " + n);
		displayFib(fib);
		System.out.print(" with " + addCount + " additions");
		if (multCount > 0) System.out.print(", " + multCount + " multiplies");
		System.out.print(" in " + opDuration + " microseconds");
		
		System.out.println();
	}
	
	protected void displayFib(BigInteger fib) {
		if (fib.bitLength() <= 64)
			System.out.print(" = " + fib);
		else
			System.out.print(" has " + fib.bitLength() + " bits");
	}

	public void display(int m, int n) {
		for (int i = m; i < n; ++i) {
			display(i);
		}
	}
	
	protected abstract BigInteger fib(int n);
	
	public static class DirectRecursion extends Fibonacci {
		protected BigInteger fib(int n) {
			if (n < 2) return valueOf(n);		
			++ addCount;
			return fib(n-2).add(fib(n-1));
		}		
	}
	
	static final ArrayList<BigInteger> FIBS = new ArrayList<BigInteger>(100);
	
	// static initialisation of table for memoisation
	
	static {
		FIBS.add(ZERO);
		FIBS.add(ONE);
		FIBS.add(ONE);
		FIBS.add(valueOf(2));
		FIBS.add(valueOf(3));
		FIBS.add(valueOf(5));
		FIBS.add(valueOf(8));
		FIBS.add(valueOf(13));
		FIBS.add(valueOf(21));
		FIBS.add(valueOf(34));
		FIBS.add(valueOf(55));
		FIBS.add(valueOf(89));
		
	}

	public static class MemoisedRecursion extends Fibonacci {
		
		protected BigInteger fib(int n) {
			if (n < FIBS.size())
				return FIBS.get(n);
			return calculateAndStore(n);
		}
		protected BigInteger calculateAndStore(int n) {
			++ addCount;
			BigInteger result = fib(n-1).add(fib(n-2));
			FIBS.add(result);
			return result;
		}		
	}
	
	public static class MemoisedIteration extends MemoisedRecursion {
		@Override
		protected BigInteger calculateAndStore(int n) {
			for (int i = FIBS.size(); i <= n; ++i) {
				++ addCount;
				BigInteger result = fib(i-1).add(fib(i-2));
				FIBS.add(result);
			}
			return fib(n);
		}				
	}
	
	public static class DirectIteration extends Fibonacci {

		@Override
		protected BigInteger fib(int n) {
			BigInteger f1 = ZERO;
			if (n == 0) return f1;
			
			BigInteger f2 = ONE;
			while (n > 1) {
				BigInteger f0 = f1;
				f1 = f2;
				f2 = f1.add(f0);
				++addCount;
				--n;
			}
			
			return f2;
		}
		
	}

	public static class FastRecursion extends Fibonacci {
		
		BigInteger[] fibPai0;

		@Override
		protected BigInteger fib(int n) {
			if (n == 0) return ZERO;
			return fibPair(n)[1];
		}
		
		protected BigInteger[] fibPair(int n) {
			final BigInteger[] result = new BigInteger[2];
			if (n == 1) {
				result[0] = ZERO;
				result[1] = ONE;
			}
			else if (n%2 == 0) {
				final BigInteger[] f = fibPair(n/2);
				
				BigInteger f0Squared = f[0].multiply(f[0]);
				BigInteger f1Squared = f[1].multiply(f[1]);
				BigInteger twiceF0ByF1 = f[1].multiply(f[0]).shiftLeft(1);
				
				result[0] = f1Squared.add(f0Squared);
				result[1] = twiceF0ByF1.add(f1Squared);
				addCount += 2;
				multCount += 3;
			}
			else {
				final BigInteger[] f = fibPair(n-1);
				
				result[0] = f[1];
				result[1] = f[1].add(f[0]);
				++addCount;
			}
			return result;
		}
	}

			

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Fibonacci directRecursive = new Fibonacci.DirectRecursion();
		Fibonacci memoRecursive = new Fibonacci.MemoisedRecursion();
		Fibonacci memoIterative = new Fibonacci.MemoisedIteration();
		Fibonacci directIterative = new Fibonacci.DirectIteration();
		Fibonacci fastRecursive = new Fibonacci.FastRecursion();
		
		Fibonacci[] fibs = {
				directRecursive,
				memoRecursive,
				memoIterative,
				directIterative,
				fastRecursive}; //,
		
		for (Fibonacci fib: fibs) {
			fib.display(0, 13);
			System.out.println();			
		};
		
		
		System.out.println();

		for (Fibonacci fib: fibs) {
			fib.display(35);
		};

		System.out.println();

		memoRecursive.display(2000);
		memoIterative.display(2000);

		System.out.println();

		memoIterative.display(36000);
		directIterative.display(36000);
		fastRecursive.display(36000);
		
		// time and space exhaustion
//		directRecursive.display(100);  // too slow
//		memoRecursive.display(2500);  // too large a stack
//		memoIterative.display(40000);  // too large a heap

		directIterative.display(100000); 
		fastRecursive.display(100000); 
	}

}
