package ru.stqa.cucumber;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.maven.app.Application;
import ru.stqa.maven.tests.TestBase;

import java.io.IOException;
import java.util.Properties;

public class CucumberTestBase {
    protected WebDriver driver;
    protected Properties props;
    protected static Application app;

    public CucumberTestBase() throws IOException {
        driver = new ChromeDriver();
        props = new Properties();
        props.load(TestBase.class.getResourceAsStream("resources/appSettings.properties"));
        app = new Application(driver, props);
    }
}
