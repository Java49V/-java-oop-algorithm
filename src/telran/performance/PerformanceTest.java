package telran.performance;

public abstract class PerformanceTest {
    private String testName;
    private int nRuns;

    public PerformanceTest(String testName, int nRuns) {
        this.testName = testName;
        this.nRuns = nRuns;
    }

    protected abstract void runTest();

    public void run() {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < nRuns; i++) {
            runTest();
        }

        long endTime = System.currentTimeMillis();
        long runningTime = endTime - startTime;

        System.out.println("Test Name: " + testName);
        System.out.println("Number of Runs: " + nRuns);
        System.out.println("Running Time: " + runningTime + " ms");
    }
}
