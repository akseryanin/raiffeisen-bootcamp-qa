package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class TestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    @Before
    public void start()
    {
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }
    @After
    public void stop()
    {
        driver.quit();
        driver = null;
    }

    public boolean isElementPresent(By locator) {
        try {
            //ждем пока найдется элемент, если нет, вылетит исключение
            driver.findElement(locator);
            return true;
        }
        catch (NoSuchElementException ex) {
            return false;
        }
    }
    public void sleep(int n)
    {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }
}
