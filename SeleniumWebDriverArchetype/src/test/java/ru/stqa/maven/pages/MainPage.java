package ru.stqa.maven.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Properties;


public class MainPage extends Page {

    public MainPage(WebDriver driver, Properties props){
        super(driver, props);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="#box-most-popular li.product:first-child")
    private WebElement product;


    public void open(){
        driver.get(props.getProperty("baseURL") + props.getProperty("lang") + "/");
    }

    public void pickProduct(){
        product.click();
    }

}