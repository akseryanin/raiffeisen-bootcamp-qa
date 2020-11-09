package ru.stqa;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    private WebDriver driver;
    @Before
    public void start()
    {
        driver = new ChromeDriver();
    }
    @Test
    public void shouldAnswerWithTrue() {
        driver.get("https://yandex.ru");
    }
    @After
    public void stop()
    {
        driver.quit();
        driver = null;
    }
}
