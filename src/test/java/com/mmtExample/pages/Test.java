package com.mmtExample.pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class Test {




    public static void main(String args[]) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.makemytrip.com/");
        //  Thread.sleep(60000);
        driver.findElement(By.xpath("//li[contains(@class,'userLoggedOut')]")).click();
        //driver.findElement(By.xpath("//div[@class='chHeaderContainer']//li[2]")).click();
        driver.findElement(By.xpath("//li[@class='menu_Hotels']")).click();
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder,'city')]"))).sendKeys("Mumbai");
        wait.until(ExpectedConditions.textMatches(By.xpath("//p[text()='SUGGESTIONS ']//..//..//li[1]"),Pattern.compile("^Mumbai")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='SUGGESTIONS ']//..//..//li[1]"))).click();
        System.out.println(driver.findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"]//div//span)[1]")).getText());
        System.out.println(driver.findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"]//div)[1]")).getText());
        String[] a= driver.findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"]//div)[1]")).getText().split("(?<=\\D)(?=\\d)");
        System.out.println(a[0]);
        System.out.println(a[1]);
        driver.findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"]//div)[1]//..//..//div[text()='25']")).click();
        driver.findElement(By.xpath("(//div[@class=\"DayPicker-Caption\"]//div)[1]//..//..//div[text()='27']")).click();
        driver.findElement(By.id("guest")).click();
        driver.findElement(By.cssSelector("ul[class^=\"guestCounter\"]:nth-of-type(1)>li:nth-of-type(3)")).click();
        driver.findElement(By.cssSelector("ul[class^=\"guestCounter\"]:nth-of-type(2)>li:nth-of-type(2)")).click();
        Select select= new Select(driver.findElement(By.xpath("//select[@id=\"0\"][@class=\"ageSelectBox\"]")));
        select.selectByVisibleText("7");
        driver.findElement(By.xpath("//button[text()='APPLY']")).click();
        driver.findElement(By.xpath("//span[text()='Travelling For']")).click();
        driver.findElement(By.xpath("//li[text()='Work']")).click();
        driver.findElement(By.xpath("//button[text()='Search']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"min\"]")));
        driver.findElement(By.xpath("//input[@name=\"min\"]")).sendKeys("1000");
        driver.findElement(By.xpath("//input[@name=\"max\"]")).sendKeys("25000");
        driver.findElement(By.xpath("//div[@class=\"range\"]//button")).click();

        //driver.findElement(By.xpath("//span[text()='4 & above (Very Good)']")).click();
//            JavascriptExecutor js= (JavascriptExecutor) driver;
//            Boolean b= js.executeScript("return document.readyState").equals("complete");
        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) driver;
        boolean b = false;
        while(!b){
            System.out.println(b + " before");
           b = javascriptExecutor.executeScript("return document.readyState").equals("complete");
            System.out.println(b + " after");
            System.out.println("running");
        }

        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='4 & above (Very Good)']"))).click();
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@id=\"USER_RATING\"]//label)[1]"))).click();
//        wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//div[@class=\"range\"]//button")))));
        WebElement element = driver.findElement(By.xpath("//span[text()='4 & above (Very Good)']"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
        List<WebElement> ele = driver.findElements(By.xpath("//div[@id=\"hotelListingContainer\"]"));

        System.out.println(ele.size());
        System.out.println(ele.get(0).getText());



        //driver.findElement(By.xpath("(//div[@id='USER_RATING']//ul//input)[2]")).click();

        ////button[text()='Search']
        ////li[text()='Work']

        //id="guest"
        ////div[contains(@aria-label,"May 12 2022")]
        //(//div[@class="DayPicker-Caption"]//div)[1]//..//..//div[text()='7']



    }


}
