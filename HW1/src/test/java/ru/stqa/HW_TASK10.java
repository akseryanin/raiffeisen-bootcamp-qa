package ru.stqa;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class HW_TASK10  extends TestBase{
    @Test
    public void checkRefsInNewWindow(){

        loginAdmin();
        wait.until(titleIs("My Store"));
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        //перейдем на страницу добавления
        driver.findElement(By.cssSelector("#content .button")).click();
        wait.until(titleContains("Add New Country"));
        List<WebElement> refs = driver.findElements(By.cssSelector("#content table:not(#table-zones) a:not(#address-format-hint)"));

        //начнем переходить по ссылкам, так как страница не измениться, сохраним эти ссылки
        for (WebElement ref : refs){
            String durWindow = driver.getWindowHandle();
            Set<String> activeWindows = driver.getWindowHandles();
            ref.click();
            String newWindow = wait.until(anyOtherWindow(activeWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(durWindow);
        }
    }

    //модифицируем в лямбду
    public ExpectedCondition<String> anyOtherWindow(Set<String> oldWindows){
        return driver -> {
            if (driver == null)
                return null;
            Set<String> handles = driver.getWindowHandles();
            handles.removeAll(oldWindows);
            return handles.size() > 0 ? handles.iterator().next() : null;
        };

    }
}
