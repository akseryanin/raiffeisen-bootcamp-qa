package ru.stqa;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class HW_TASK4  extends TestBase{
    @Test
    public void checkStickers()
    {
        driver.get("http://localhost/litecart/en/");

        // найдем азделы Most Popular, Campaigns, Latest Products где-нибудь на странице, box -> content -> product
        List<WebElement> products = driver.findElements(By.cssSelector(".box .content .product"));

        for (WebElement element : products){
            //где нибудь внутри найденного элемента найдем класс stickers
            assertTrue(element.findElements(By.cssSelector(".sticker")).size() == 1);
        }
    }
}
