package ru.stqa;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class HW_TASK9 extends TestBase{

    @Test
    public void cart(){

        for (int i =1; i<=3;i++){

            driver.get("https://litecart.stqa.ru/en/");
            wait.until(titleContains("Online Store"));
            WebElement product = driver.findElement(By.cssSelector("#box-most-popular li.product:first-child"));
            String name = product.findElement(By.cssSelector(".name")).getAttribute("textContent");
            product.click();

            wait.until(attributeToBe(By.cssSelector("h1"), "textContent", name));

            // проверяем, есть ли на странице товара список с выбором размера
            if (driver.findElements(By.cssSelector("option")).size() > 0){
                Select size = new Select(driver.findElement(By.cssSelector("[name='options[Size]']")));
                size.selectByValue("Small");
            }

            String count = driver.findElement(By.cssSelector("#cart .quantity")).getAttribute("textContent");
            WebElement sendButton = driver.findElement(By.cssSelector("form[name=buy_now_form] button[type=submit]"));
            wait.until(elementToBeClickable(sendButton));
            driver.findElement(By.cssSelector("form[name=buy_now_form] button[type=submit]")).sendKeys(Keys.RETURN);
            wait.until(not(attributeToBe(By.cssSelector("#cart .quantity"),"textContent",count)));
        }

        driver.findElement(By.cssSelector("#cart .link")).click();

        int rows = driver.findElements(By.cssSelector(".dataTable tr")).size() - 5;
        for (int i=1;i<=rows;i++){

            WebElement table = driver.findElement(By.cssSelector(".dataTable"));
            driver.findElement(By.cssSelector("button[name=remove_cart_item]")).sendKeys(Keys.RETURN);

            wait.until(stalenessOf(table));

            // не проверяем после удаления последнего элемента, так как таблицы нет
            if (i!=rows){
                wait.until(visibilityOfElementLocated(By.cssSelector(".dataTable")));
            }

        }


    }
}
