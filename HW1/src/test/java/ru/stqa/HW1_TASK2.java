package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import java.io.IOException;

public class HW1_TASK2
{
    private WebDriver driver;
    private WebDriverWait wait;
    @Before
    public void start()
    {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }
    @Test
    public void loginAdmin() {
        try {
            driver.get("http://localhost/litecart/admin");
            WebElement email = driver.findElement(By.name("username"));
            email.sendKeys("admin");
            WebElement pass = driver.findElement(By.name("password"));
            pass.sendKeys("admin");
            driver.findElement(By.name("login")).click();
            wait.until(titleIs("My Store"));

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            driver.quit();
            driver = null;
            assert 1 == 0;
        }
    }
    @After
    public void stop()
    {
        driver.quit();
        driver = null;
    }
}
