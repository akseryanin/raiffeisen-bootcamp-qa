package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 * Unit test for simple App.
 */
public class AppTest 
{
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
