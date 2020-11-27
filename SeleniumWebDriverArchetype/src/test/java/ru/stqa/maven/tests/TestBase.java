package ru.stqa.maven.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.maven.app.Application;

import java.io.IOException;
import java.util.Properties;

public class TestBase {

    private WebDriver driver;
    public Application app;
    private Properties props;

    @Before
    public void start() throws IOException {
        driver = new ChromeDriver();
        props = new Properties();
        props.load(TestBase.class.getResourceAsStream("/appSettings.properties"));
        app = new Application(driver, props);
    }

    @After
    public void stop(){
        driver.quit();
        driver=null;
    }
}

