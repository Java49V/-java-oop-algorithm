package telran.performance;

import java.util.Arrays;
import telran.strings.*;

public class JoinStringsPerformanceAppl {

    private static final int N_STRINGS = 1000;
    private static final int N_RUNS = 1000;

    public static void main(String[] args) {
        String[] strings = getStrings();
        
        // List of classes to test
        String[] classNames = { 
            "telran.strings.JoinStringsImpl",
            "telran.strings.JoinStringsBuilderImpl"
        };
        
        for (String className : classNames) {
            try {
                // Create an instance of the class using reflection
                Class<?> clazz = Class.forName(className);
                Object instance = clazz.getDeclaredConstructor().newInstance();
                
                if (instance instanceof JoinStrings) {
                    JoinStrings joinStringsInstance = (JoinStrings) instance;
                    String testName = getTestName(clazz.getSimpleName());
                    JoinStringsPerformanceTest test = 
                        new JoinStringsPerformanceTest(testName, N_RUNS, strings, joinStringsInstance);
                    test.run();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String getTestName(String className) {
        return String.format("%s; Number of the strings is %d", className, N_STRINGS);
    }

    private static String[] getStrings() {
        String[] res = new String[N_STRINGS];
        Arrays.fill(res, "string");
        return res;
    }

}

//package telran.performance;
//
//import java.util.Arrays;
//
//import telran.strings.*;
//
//public class JoinStringsPerformanceAppl {
//
//	private static final int N_STRINGS = 1000;
//	private static final int N_RUNS = 1000;
//    //FIXME rewrite the code by applying class reflection
//	// to get rid of JoinString implementations
//	public static void main(String[] args) {
//		String[] strings = getStrings();
//		String testNameString = getTestName("JoinStringsImpl");
//		String testNameStringBuilder = getTestName("JoinStringsBuilderImpl");
//		JoinStringsImpl jsi = new JoinStringsImpl();
//		JoinStringsBuilderImpl jsbi = new JoinStringsBuilderImpl();
//		JoinStringsPerformanceTest testStringBuilder =
//				new JoinStringsPerformanceTest(testNameStringBuilder, N_RUNS,
//strings, jsbi);
//		JoinStringsPerformanceTest testString =
//				new JoinStringsPerformanceTest(testNameString, N_RUNS,
//strings, jsi);
//		testStringBuilder.run();
//		testString.run();
//		
//	}
//
//	private static String getTestName(String className) {
//		
//		return String.format("%s; Number of the strings is %d", className, N_STRINGS);
//	}
//
//	private static String[] getStrings() {
//		String[] res = new String[N_STRINGS];
//		Arrays.fill(res, "string");
//		return res;
//	}
//
//}
