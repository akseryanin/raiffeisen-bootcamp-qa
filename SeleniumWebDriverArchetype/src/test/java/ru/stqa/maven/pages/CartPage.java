package ru.stqa.maven.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Properties;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class CartPage extends Page{

    public CartPage(WebDriver driver, Properties props){
        super(driver, props);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css=".dataTable")
    private WebElement productTable;

    @FindBy(css=".dataTable tr")
    private List<WebElement> productRows;

    @FindBy(css="[name=remove_cart_item]")
    private WebElement removeButton;

    public int getProductsQuantityFromTable(){
        return productRows.size() - 5;
    }

    public void removeProduct(){
        WebElement table = driver.findElement(By.cssSelector(".dataTable"));
        removeButton.click();
        wait.until(stalenessOf(table));
    }

    public void waitProductsTablesUpdated(){
        wait.until(visibilityOf(productTable));
    }
}
