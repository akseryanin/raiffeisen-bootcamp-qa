package ru.stqa;

import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.Select;



import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class HW_TASK9 extends TestBase{

    //иногда так случается, что клик не происходит в браузере, а иногда кликает
    //скорее всего JS скрипт не привязывается к кнопке
    @Test
    public void checkAddDeleteProducts(){

        for (int i = 0; i < 3; ++i){

            driver.get("http://localhost/litecart/en/");
            wait.until(titleContains("Online Store"));
            WebElement product = driver.findElement(By.cssSelector("#box-most-popular li.product:first-child"));
            String name = product.findElement(By.cssSelector(".name")).getAttribute("textContent");
            product.click();

            wait.until(attributeToBe(By.cssSelector("h1"), "textContent", name));

            // проверяем, есть ли на странице товара обязательная опция размера
            if (driver.findElements(By.cssSelector("option")).size() > 0){
                Select size = new Select(driver.findElement(By.cssSelector("[name='options[Size]']")));
                size.selectByValue("Small");
            }

            //sleep(500); //костыль который иногда чинит работу
            String count = driver.findElement(By.cssSelector("#cart .quantity")).getAttribute("textContent");
            WebElement sendButton = driver.findElement(By.cssSelector("button[name=add_cart_product]"));
            wait.until(elementToBeClickable(sendButton));
            sendButton.click();
            wait.until(not(attributeToBe(By.cssSelector("#cart .quantity"),"textContent",count)));
        }

        driver.findElement(By.cssSelector("#cart .link")).click();
        wait.until(presenceOfElementLocated(By.cssSelector(".dataTable tr")));

        int rows = driver.findElements(By.cssSelector(".dataTable tr")).size() - 5;
        for (int i = 0; i < rows; ++i){

            //sleep(500); //костыль который иногда чинит работу
            WebElement table = driver.findElement(By.cssSelector(".dataTable"));
            wait.until(elementToBeClickable(By.cssSelector("button[name=remove_cart_item]")));
            driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();

            //обновление таблицы (она исчезает)
            wait.until(stalenessOf(table));
            // не проверяем после удаления последнего элемента, так как таблицы нет
            if (i != rows - 1){
                wait.until(visibilityOfElementLocated(By.cssSelector(".dataTable")));
            }

        }


    }
}
