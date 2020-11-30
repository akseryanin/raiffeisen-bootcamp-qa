package ru.stqa.cucumber;

import io.cucumber.java8.En;
import ru.stqa.maven.app.Application;

import java.io.IOException;

public class ProductsAddStepdefs extends CucumberTestBase implements En {
    private Integer count;

    public ProductsAddStepdefs() throws IOException {
        super();
        Then("^while size of bag is not (\\d+) - add new product$", (Integer countProducts) -> {
            app.addProductsToCart(countProducts);
            count = countProducts;
        });
        And("^open bag page$", () -> {
            app.goToCart();
        });
        And("^delete all products$", () -> {
            app.removeProducts(count);
        });
        Given("^open main page$", () -> {
            app.open();
        });
    }
}
