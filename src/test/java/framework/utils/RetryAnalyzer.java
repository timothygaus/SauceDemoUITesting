package framework.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
   private int retryCount = 0;
   private static final int MAX_RETRY_COUNT = 2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCount < MAX_RETRY_COUNT) {
            System.out.println("Retrying " + iTestResult.getName());
            retryCount++;
            return true;
        }
        return false;
    }
}
