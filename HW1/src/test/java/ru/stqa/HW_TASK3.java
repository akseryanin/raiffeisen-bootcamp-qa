package ru.stqa;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.junit.Assert.assertTrue;

public class HW_TASK3 extends TestBase
{
    @Test
    public void clickedAllSections() throws InterruptedException {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        WebElement allSections = driver.findElement(By.cssSelector("#box-apps-menu"));
        List<WebElement> sections = allSections.findElements(By.cssSelector("#app-"));

        for (WebElement section : sections) {
            String locatorTitle = "td#content > h1";

            section.click();
            assertTrue(isElementPresent(By.cssSelector(locatorTitle)));
            List<WebElement> refs = section.findElements(By.cssSelector(".docs ul > li"));
            for (WebElement ref : refs){
                ref.click();
                assertTrue(isElementPresent(By.cssSelector(locatorTitle)));
            }

        }

    }
}
