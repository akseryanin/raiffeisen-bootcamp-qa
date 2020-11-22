package ru.stqa;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.File;

public class HW_TASK8 extends TestBase{
    @Test
    public void addNewProduct(){
        loginAdmin();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");

        int k = count(By.cssSelector(".dataTable tr.row"));

        // нажимаем кнопку создания
        driver.findElement(By.cssSelector("#content a.button:last-child")).click();

        // General
        driver.findElement(By.cssSelector("[name=status][value='1']")).click();
        driver.findElement(By.name("[name[en]")).sendKeys("New duck");
        driver.findElement(By.name("code")).sendKeys("123");
        driver.findElement(By.cssSelector("[data-name='Rubber Ducks']")).click();
        driver.findElement(By.cssSelector("[value='1-2']")).click();

        clearAndFillTheField(By.cssSelector("[name=quantity]"), "1.50");
        selectByValue(By.cssSelector("[name=sold_out_status_id]"),"2");

        driver.findElement(By.cssSelector("[type=file]")).sendKeys((new File("1.jpg").getAbsolutePath()));
        driver.findElement(By.cssSelector("[name=date_valid_from]")).sendKeys("14.12.2016");
        driver.findElement(By.cssSelector("[name=date_valid_to]")).sendKeys("27.12.2016");

        // Information
        driver.findElement(By.cssSelector("[href='#tab-information']")).click();

        selectByValue(By.cssSelector("[name=manufacturer_id]"),"1");

        driver.findElement(By.cssSelector("[name=keywords]")).sendKeys("duck ducks");
        driver.findElement(By.cssSelector("[name='short_description[en]']")).sendKeys("duck");
        driver.findElement(By.cssSelector("[name='description[en]']")).sendKeys("new batman duck for female");
        driver.findElement(By.cssSelector("[name='head_title[en]']")).sendKeys("new batman duck");
        driver.findElement(By.cssSelector("[name='meta_description[en]']")).sendKeys("new duck");

        // Prices
        driver.findElement(By.cssSelector("[href='#tab-prices']")).click();

        clearAndFillTheField(By.cssSelector("[name='purchase_price']"), "100");
        selectByValue(By.cssSelector("[name=purchase_price_currency_code]"),"EUR");

        driver.findElement(By.cssSelector("[name='prices[USD]']")).sendKeys("120");
        driver.findElement(By.cssSelector("[name='prices[EUR]']")).sendKeys("100");

        driver.findElement(By.cssSelector("#add-campaign")).click();

        setDatepicker(By.cssSelector("[name*=start_date]"), "2016-12-15T00:00");
        setDatepicker(By.cssSelector("[name*=end_date]"), "2016-12-17T23:59");
        clearAndFillTheField(By.cssSelector("[name*=percentage]"), "10");

        // сохранение
        driver.findElement(By.cssSelector("[name=save]")).click();
        wait.until(presenceOfElementLocated(By.cssSelector(".dataTable")));

        // получаем количество строк после добавления товара
        int kNew = count(By.cssSelector(".dataTable tr.row"));

        assertEquals(k+1, kNew);
    }

    private void assertEquals(int i, int kNew) {
    }

    public void setDatepicker(By locator, String date) {

        WebElement datapiker = driver.findElement(locator);
        JavascriptExecutor.class.cast(driver).executeScript("arguments[0].value=arguments[1]", datapiker, date);
    }

    public void selectByValue(By locator, String value){

        Select field = new Select(driver.findElement(locator));
        field.selectByValue(value);

    }

    public void clearAndFillTheField(By locator, String value){

        WebElement field = driver.findElement(locator);
        field.clear();
        field.sendKeys(value);

    }

    public int count(By locator){

        return driver.findElements(locator).size();

    }
}
