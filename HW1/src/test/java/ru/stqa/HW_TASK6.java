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

        //ссылка и имя
        String Link = product.findElement(By.cssSelector(".link")).getAttribute("href");
        String Name = product.findElement(By.cssSelector(".name")).getAttribute("textContent");

        WebElement regularPrice = product.findElement(By.cssSelector(".regular-price"));
        WebElement campaignPrice = product.findElement(By.cssSelector(".campaign-price"));

        //значения цен
        String regularPriceValue = regularPrice.getAttribute("textContent");
        String campaignPriceValue = campaignPrice.getAttribute("textContent");

        //чтобы определить стиль возьмем тег и класс
        String regularPriceClass = regularPrice.getAttribute("class");
        String campaignPriceClass = campaignPrice.getAttribute("class");
        String regularPriceTag = regularPrice.getAttribute("tagName");
        String campaignPriceTag = campaignPrice.getAttribute("tagName");

        //получение цвета
        String regularPriceColorStr = regularPrice.getCssValue("color");
        String campaignPriceColorStr = campaignPrice.getCssValue("color");
        ColorViewModel regularPriceColor = parseColor(regularPriceColorStr);
        ColorViewModel campaignPriceColor = parseColor(campaignPriceColorStr);

        //проверка серости
        assertTrue(regularPriceColor.red == regularPriceColor.blue && regularPriceColor.blue == regularPriceColor.green);
        //проверка красности
        assertTrue(campaignPriceColor.blue == 0 && campaignPriceColor.green == 0);
        //получение размера шрифта
        String regularPriceHightStr = regularPrice.getCssValue("font-size");
        Double regularPriceHight = Double.parseDouble(regularPriceHightStr.substring(0, regularPriceHightStr.length() - 2));
        String campaignPriceHightStr = campaignPrice.getCssValue("font-size");
        Double campaignPriceHight = Double.parseDouble(campaignPriceHightStr.substring(0, campaignPriceHightStr.length() - 2));
        //проверка что акционная цена больше обычной по шрифту
        assertTrue(regularPriceHight < campaignPriceHight);

        //жирный ли шрифт
        String campaignPriceW = campaignPrice.getCssValue()

        product.click();

        // страница товара, ссылку берем как ссылку страницы
        String Link1 = driver.findElement(By.cssSelector("link[rel=canonical")).getAttribute("href");
        String Name1 = driver.findElement(By.cssSelector("#box-product .title")).getAttribute("textContent");

        WebElement regularPrice1 = driver.findElement(By.cssSelector("#box-product .regular-price"));
        WebElement campaignPrice1 = driver.findElement(By.cssSelector("#box-product .campaign-price"));

        String regularPriceValue1 = regularPrice1.getAttribute("textContent");
        String campaignPriceValue1 = campaignPrice1.getAttribute("textContent");
        String regularPriceClass1 = regularPrice1.getAttribute("class");
        String campaignPriceClass1 = campaignPrice1.getAttribute("class");
        String regularPriceTag1 = regularPrice1.getAttribute("tagName");
        String campaignPriceTag1 = campaignPrice1.getAttribute("tagName");
        String regularPriceColorStr1 = regularPrice1.getCssValue("color");
        String campaignPriceColorStr1 = regularPrice1.getCssValue("color");
        ColorViewModel regularPriceColor1 = parseColor(regularPriceColorStr1);
        ColorViewModel campaignPriceColor1 = parseColor(campaignPriceColorStr1);
        assertTrue(regularPriceColor1.red == regularPriceColor1.blue && regularPriceColor1.blue == regularPriceColor1.green);
        assertTrue(campaignPriceColor1.blue == 0 && campaignPriceColor1.green == 0);


        assertEquals(Link, Link1); //проверка ссылок товара
        assertEquals(Name, Name1); //проверка названия товара
        assertEquals(regularPriceValue, regularPriceValue1); //проверка совпадения обычной цены товара
        assertEquals(campaignPriceValue, campaignPriceValue1); //проверка совпадения скидочной цены товара
        //проверка одного и того же стиля обычной цены
        assertEquals(regularPriceClass, regularPriceClass1);
        assertEquals(regularPriceTag, regularPriceTag1);
        //проверка одного и того же стиля скидочной цены
        assertEquals(campaignPriceClass, campaignPriceClass1);

        assertEquals(campaignPriceTag, campaignPriceTag1);
    }
}

