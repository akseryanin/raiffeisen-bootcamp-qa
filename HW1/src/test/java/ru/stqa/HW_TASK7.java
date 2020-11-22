package ru.stqa;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.Calendar;

import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class HW_TASK7 extends TestBase{
    @Test
    public void regist(){

        driver.get("https://litecart.stqa.ru/en/");
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(calendar.HOUR_OF_DAY);
        int minute = calendar.get(calendar.MINUTE);
        int second = calendar.get(calendar.SECOND);

        String email = "akseryanin_"+hour+"_"+minute+"_"+second+"@yandex.ru";
        String password = "111111";

        registration(email,password);
        logout();
        login(email,password);
        logout();

    }

    private void registration(String email,String password){
        //переходим на страницу регистрации
        driver.findElement(By.cssSelector("#box-account-login a")).click();

        driver.findElement(By.cssSelector("[name='tax_id']")).sendKeys("123456789");
        driver.findElement(By.cssSelector("[name='company']")).sendKeys("HSE");
        sleep(500);
        driver.findElement(By.cssSelector("[name='firstname']")).sendKeys("Alexander");
        driver.findElement(By.cssSelector("[name='lastname']")).sendKeys("Seryanin");
        sleep(500);
        driver.findElement(By.cssSelector("[name='address1']")).sendKeys("Moscow Polyani 7");
        driver.findElement(By.cssSelector("[name='address2']")).sendKeys("Moscow ul Skobelebckayz d 1");
        sleep(500);
        driver.findElement(By.cssSelector("[name='postcode']")).sendKeys("11111");
        driver.findElement(By.cssSelector("[name='city']")).sendKeys("Moscow");
        sleep(500);
        Select country = new Select(driver.findElement(By.cssSelector("select[name=country_code]")));
        country.selectByValue("US");
        wait.until(textToBePresentInElementLocated(By.cssSelector("select[name=zone_code]"),"Alabama"));
        Select zone = new Select(driver.findElement(By.cssSelector("select[name=zone_code]")));
        zone.selectByValue("AK");
        sleep(500);
        driver.findElement(By.cssSelector("[name=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("[name=phone]")).sendKeys("+19268472679");
        sleep(500);
        driver.findElement(By.cssSelector("[name=newsletter]")).click();
        sleep(500);
        driver.findElement(By.cssSelector("[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("[name=confirmed_password]")).sendKeys(password);
        driver.findElement(By.cssSelector("[name=create_account]")).click();
        //найдем кнопку logout
        wait.until(presenceOfElementLocated(By.cssSelector("#box-account li:last-child")));
    }

    private void login(String email,String password){

        driver.findElement(By.cssSelector("[name=email]")).sendKeys(email);
        driver.findElement(By.cssSelector("[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("[name=login]")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("#box-account li:last-child")));

    }

    private void logout(){

        driver.findElement(By.cssSelector("#box-account li:last-child a")).click();
        wait.until(presenceOfElementLocated(By.cssSelector("[name=email]")));
    }

}
