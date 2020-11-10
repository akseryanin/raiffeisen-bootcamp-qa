package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HW1_TASK1
{
    private WebDriver driver;
    @Before
    public void start()
    {
        driver = new ChromeDriver();
    }
    @Test
    public void sampleTest() {
        driver.get("https://yandex.ru");
    }
    @After
    public void stop()
    {
        driver.quit();
        driver = null;
    }
}
