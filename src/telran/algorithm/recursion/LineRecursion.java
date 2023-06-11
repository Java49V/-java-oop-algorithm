package telran.algorithm.recursion;

public class LineRecursion {
	public static long factorial(int n) {
		if(n < 0) {
			throw new IllegalArgumentException("Cannot be negative value");
		}
		long res = 1;
		if(n > 1) {
			res = n*factorial(n - 1);
		}
		return res;
	}
	public static long pow(int a, int b) {

		// HW #18
		//Limitations:
		// 1. no cycles
		// 2. only + or - for arithmetic operations
		if (b < 0) {
			throw new IllegalArgumentException();
		}
		long res = 1;
		if (b > 0) {
			res = multiply(a, pow(a, b - 1));
		}
		return res;
	}

	private static int multiply(int a, long b) {

		int res = 0;
		if (b != 0) {
			res = b < 0 ? multiply(-a, -b): a + multiply(a, b - 1);
		}
		

		return res;
	}

	public static long sum(int[] array) {
		return sum(0, array);
	}
	private static long sum(int firstIndex, int[] array) {
		long sum = 0;
		if (firstIndex < array.length) {
			sum = array[firstIndex] + sum(firstIndex + 1, array);
		}
		return sum;
	}
	public static int[] reverse(int[] array) {
		return reverse(array, 0, array.length - 1);
	}
	private static int[] reverse(int[] array, int left, int right) {
		
		if(left < right) {
			array[left] = array[left] + array[right];
			array[right] = array[left] - array[right];
			array[left] = array[left] - array[right];
			reverse(array, left + 1, right - 1);
		}
		return array;
	}
	

		public static long square(int x) { 

		//no cycles 

		//no * , / operators 

		//no additional functions 

		//no static fields  

		//returns x ^ 2
			// (x-1)^2 = x^2 -2x + 1; => x^2 = (x-1)^2 + 2x -1
			long res = 0; 
			if(x != 0) {
				res = x < 0 ? square(-x) : x + x - 1 + square(x - 1);
			}

		return res; 

		} 

		public static boolean isSubstring(String string, String substr) { 

		//  write function 

		// returns true if a given 'substr' is indeed the //substring of a given `string` 

		/* Challenges: 1. To apply only following methods of the class String: charAt(int ind);  

		String substring(int ind); 

		 int length(); 

		2. No cycles;*/ 
			boolean res = false;
			if (string.length() >= substr.length()) {
				res = isEqual(string, substr) ? true : isSubstring(string.substring(1), substr);
			} 

			return res;

		}

		private static boolean isEqual(String str, String substr) {
			boolean res = false;
			if (substr.length() == 0) {
				res = true;
			} else if (str.charAt(0) == substr.charAt(0)) {
				res = isEqual(str.substring(1), substr.substring(1));
			}
			
			return res;
		}
	
}
