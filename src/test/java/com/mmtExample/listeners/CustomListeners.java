package com.mmtExample.listeners;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.MDC;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class CustomListeners implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String testCase = result.getMethod().getMethodName();
        MDC.put("testCaseName", testCase);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String failedMethod= result.getName().trim();
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver)context.getAttribute("driver");
        String fileName= "output/images/"+failedMethod  + "_sceenshot_"+System.currentTimeMillis();
        takeScreenShot(driver,fileName );
    }


    public void takeScreenShot(WebDriver driver, String fileName){

        File file= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(file, new File(fileName+".png"));
        }catch (IOException e){
            System.out.println("unable to create file :: " + fileName+".png");
        }

    }

}
