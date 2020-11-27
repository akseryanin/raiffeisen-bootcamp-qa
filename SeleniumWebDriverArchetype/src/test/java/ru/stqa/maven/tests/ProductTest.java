package ru.stqa.maven.tests;

import org.junit.Test;

public class ProductTest extends TestBase {

    @Test
    public void addRemoveProduct(){

        app.open();
        app.addProductsToCart(3);
        app.goToCart();
        app.removeProducts(app.getAddedProductsQuantity());
    }
}