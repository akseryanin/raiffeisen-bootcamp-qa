package ru.stqa.maven.app;

import org.openqa.selenium.WebDriver;
import ru.stqa.maven.pages.*;

import java.util.Properties;

public class Application{

    private MainPage mainPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private Properties props;


    public Application(WebDriver driver, Properties _props){
        props = _props;
        mainPage = new MainPage(driver, props);
        productPage = new ProductPage(driver, props);
        cartPage = new CartPage(driver, props);
    }

    public void open(){
        mainPage.open();
    }

    private void addProductToCart(){
        mainPage.pickProduct();
        productPage.addToCart();
    }

    public void addProductsToCart(int number){
        for (int i = 1; i <= number; ++i){
            addProductToCart();
            if (i != number){
                open();
            }
        }
    }

    public void goToCart(){
        productPage.goToCart();
    }

    public int getAddedProductsQuantity(){
        return cartPage.getProductsQuantityFromTable();
    }

    public void removeProducts(int number){
        for (int i = 1; i <= number; ++i){
            cartPage.removeProduct();
            // не проверяем после удаления последнего элемента, так как таблицы нет
            if (i != number){
                cartPage.waitProductsTablesUpdated();
            }
        }
    }
    //немного заснем для стабильности
    private void sleep(int n){
        try{
            Thread.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
