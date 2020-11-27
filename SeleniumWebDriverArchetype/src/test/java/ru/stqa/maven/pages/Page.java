package ru.stqa.maven.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Properties props;

    public Page(WebDriver driver, Properties _props){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
        props = _props;
    }
}
