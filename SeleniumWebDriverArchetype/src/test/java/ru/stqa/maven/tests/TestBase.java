package ru.stqa.maven;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static Properties props;

    @Before
    public void start() throws IOException {
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        props = new Properties();
        props.load(TestBase.class.getResourceAsStream("/appSettings.properties"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

    public void sleep(int n) {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

