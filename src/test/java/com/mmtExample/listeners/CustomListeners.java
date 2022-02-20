package com.mmtExample.listeners;

import org.apache.log4j.MDC;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String testCase = result.getMethod().getMethodName();
        MDC.put("testCaseName", testCase);
    }
}
