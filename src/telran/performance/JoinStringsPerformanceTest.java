package telran.performance;

import telran.strings.JoinStrings;

//public class JoinStringsPerformanceTest {
//
//}
public class JoinStringsPerformanceTest extends PerformanceTest {
    private JoinStrings joinStrings;
    private String[] strings;
    private String delimiter;

    public JoinStringsPerformanceTest(String testName, int nRuns, JoinStrings joinStrings, String[] strings, String delimiter) {
        super(testName, nRuns);
        this.joinStrings = joinStrings;
        this.strings = strings;
        this.delimiter = delimiter;
    }

    @Override
    protected void runTest() {
        joinStrings.join(strings, delimiter);
    }
}
