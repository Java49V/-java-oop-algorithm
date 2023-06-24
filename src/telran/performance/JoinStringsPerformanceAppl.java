package telran.performance;

import telran.strings.JoinStringsBuilderImpl;
import telran.strings.JoinStringsImpl;

public class JoinStringsPerformanceAppl {
    private static final int N_STRINGS = 1000;

    public static void main(String[] args) {
        String[] strings = new String[N_STRINGS];
        for (int i = 0; i < N_STRINGS; i++) {
            strings[i] = "Hello";
        }

        JoinStringsPerformanceTest test1 = new JoinStringsPerformanceTest("JoinStringsBuilderImpl Test", 10000,
                new JoinStringsBuilderImpl(), strings, "#");

        JoinStringsPerformanceTest test2 = new JoinStringsPerformanceTest("JoinStringsImpl Test", 10000,
                new JoinStringsImpl(), strings, "#");

        test1.run();
        test2.run();
    }
}
