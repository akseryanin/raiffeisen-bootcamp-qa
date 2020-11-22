package ru.stqa;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import static org.junit.Assert.*;
public class HW_TASK6 extends TestBase{

    @Test
    public void checkProduct(){
        driver.get("http://localhost/litecart/en/");

        // товар на главной странице
        WebElement product = driver.findElement(By.cssSelector("#box-campaigns .product"));

        //имя
        String Name = product.findElement(By.cssSelector(".name")).getAttribute("textContent");

        WebElement regularPrice = product.findElement(By.cssSelector(".regular-price"));
        WebElement campaignPrice = product.findElement(By.cssSelector(".campaign-price"));

        //получим объекты представления на главной странице
        PriceViewModel regularPriceVM = new PriceViewModel(regularPrice);
        PriceViewModel campaignPriceVM = new PriceViewModel(campaignPrice);

        product.click();

        String Name1 = driver.findElement(By.cssSelector("#box-product .title")).getAttribute("textContent");

        WebElement regularPrice1 = driver.findElement(By.cssSelector("#box-product .regular-price"));
        WebElement campaignPrice1 = driver.findElement(By.cssSelector("#box-product .campaign-price"));

        PriceViewModel regularPriceVM1 = new PriceViewModel(regularPrice1);
        PriceViewModel campaignPriceVM1 = new PriceViewModel(campaignPrice1);


        assertEquals(Name, Name1); //проверка названия товара
        //проверка совпадения цены
        assertEquals(regularPriceVM.price, regularPriceVM1.price);
        assertEquals(campaignPriceVM.price, campaignPriceVM1.price);
        //проверка на зачеркнутость и серость
        assertTrue(regularPriceVM.color.red == regularPriceVM.color.blue && regularPriceVM.color.blue == regularPriceVM.color.green);
        assertTrue(regularPriceVM1.color.red == regularPriceVM1.color.blue && regularPriceVM1.color.blue == regularPriceVM1.color.green);
     // tele
    //проверка на жирность и красность
        assertTrue(campaignPriceVM.isFat);
        assertTrue(campaignPriceVM.color.blue == 0 && campaignPriceVM.color.green == 0);
        assertTrue(campaignPriceVM1.isFat);
        assertTrue(campaignPriceVM1.color.blue == 0 && campaignPriceVM1.color.green == 0);
        //проверка что акционная цена крупнее
        assertTrue(regularPriceVM.fontSize < campaignPriceVM.fontSize);
        assertTrue(regularPriceVM1.fontSize < campaignPriceVM1.fontSize);
    }
}

