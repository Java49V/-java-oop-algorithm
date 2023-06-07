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
	    if (b < 0) {
	        throw new IllegalArgumentException("Степень не может быть отрицательным значением.");
	    }
	    long res = 1;
	    if (b > 0) {
	        res = a;
	        int count = b - 1;
	        while (count > 0) {
	            res = res + a;
	            count--;
	        }
	    }
	    return res;
//		//a - any number
//		//b - any positive number or zero
////		if (b < 0) {
////				throw new IllegalArgumentException("Pow cannot be negative value");
////		}
////		long res = 1;
////		if (b > 0) {
////			res = a * pow(a, b - 1); // a^b = a * a^(b - 1)
////		}
////		return res;
//		//TODO HW #18
//		//Limitations:
//		// 1. no cycles
//		// 2. only + or - for arithmetic operations
//		
//		return 0;
		
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
	
	static public int power(int a, int b) {
        if (b < 0) {
            throw new IllegalArgumentException("Показатель степени не может быть отрицательным.");
        }
        if (b == 0) {
            return 1;
        } else {
            return a * power(a, b - 1);
        }
    }

    public static long square(int x) {
    	if (x < 0) {
			x = -x;
		}
		long res = x;
		if (x != 0) {
			res += square(x - 1) + x - 1;
		}
		return res;
//        if (x < 0) {
//            throw new IllegalArgumentException("Input item have not negative");
//        }
//        if (x == 0) {
//            return 0;
//        } else {
//            return x + square(x - 1) + square(x - 1);
//        }
    }

    public static boolean isSubstring(String string, String substr) {
        if (substr.length() == 0) {
            return true;
        }
        if (string.length() < substr.length()) {
            return false;
        }
        if (string.charAt(0) == substr.charAt(0) && isSubstringHelper(string, substr, 0, 0)) {
            return true;
        }
        return isSubstring(string.substring(1), substr);
    }

    private static boolean isSubstringHelper(String string, String substr, int i, int j) {
        if (j == substr.length()) {
            return true;
        }
        if (i == string.length()) {
            return false;
        }
        if (string.charAt(i) == substr.charAt(j)) {
            return isSubstringHelper(string, substr, i + 1, j + 1);
        }
        return false;
    }
	
}
