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

        // Раздел Recently Viewed ================================================
        /// так как при загрузке страницы этот блок будет пустой
        /// можно этот раздел и опустить

        List<WebElement> recentProducts = driver.findElements(By.cssSelector("#box-recently-viewed-products li"));

        /*for(WebElement element:recentProducts){
            assertTrue(isOneElementPresent(element, By.cssSelector(".sticker")));
        }
        // Раздел Recently Viewed ===============================================


        // Разделы Most Popular, Campaigns, Latest Products
        List<WebElement> products = driver.findElements(By.cssSelector(".content .box .product"));

        for(WebElement element:products){
            assertTrue(isOneElementPresent(element, By.cssSelector(".sticker")));
        }*/
    }
}
