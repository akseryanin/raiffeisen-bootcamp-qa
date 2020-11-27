package ru.stqa.maven.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Properties;

import static org.openqa.selenium.support.ui.ExpectedConditions.not;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class ProductPage extends Page{

    public ProductPage(WebDriver driver, Properties props){
        super(driver, props);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="[name=add_cart_product]")
    private WebElement addProductButton;

    @FindBy(css="#cart .quantity")
    private WebElement quantity;

    @FindBy(css="#cart .link")
    private WebElement checkoutLink;

    private void selectSize(){
        if (driver.findElements(By.cssSelector("option")).size() > 0){
            Select size = new Select(driver.findElement(By.cssSelector("[name='options[Size]']")));
            size.selectByValue("Small");
        }
    }

    private void addProduct() {
        addProductButton.click();
    }

    public void addToCart() {
        selectSize();
        String countBeforeAdd = getProductsQuantity();
        addProduct();
        wait.until(not(textToBePresentInElement(quantity,countBeforeAdd)));
    }

    private String getProductsQuantity(){
        return quantity.getAttribute("textContent");
    }

    public void goToCart(){
        checkoutLink.click();
    }
}