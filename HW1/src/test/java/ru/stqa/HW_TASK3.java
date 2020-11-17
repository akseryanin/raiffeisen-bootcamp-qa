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
    public void clickedAllSections() {
        //авторизация
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        //найдем в меню все секции li
        int countSections = driver.findElements(By.cssSelector("#box-apps-menu > li")).size();
        //сразу сделает локатор для поиска заголовка
        String locatorTitle = "td#content > h1";
        for (int i = 1; i <= countSections; ++i) {
            //работа с объектами WebElement неудачная потому что после клика ссылки обновляются
            //нужно после каждого действия на странице заново искать объекты через DOM
            String sectionLocator = "#box-apps-menu > li:nth-child("+i+")";

            //кликнем по секции
            driver.findElement(By.cssSelector(sectionLocator)).click();

            //Проверим существует ли заголовок на странице
            assertTrue(isElementPresent(By.cssSelector(locatorTitle)));

            //кликов по объекту секции больше не будет, можем завести объект класса
            WebElement section = driver.findElement(By.cssSelector(sectionLocator));

            //так как будут клики, то проделаем такую же операцию с подсекциями внизу
            //посмотрев на html код нам надо спуститься на 2 уровня ниже (ul > li)
            int countRef = section.findElements(By.cssSelector("ul > li")).size();

            for (int j = 1; j <= countRef; ++j){
                //обновим секцию после клика
                section = driver.findElement(By.cssSelector(sectionLocator));

                //найдем элемент через DOM и по нему кликнем
                section.findElement(By.cssSelector("li:nth-child("+j+")")).click();

                //проверим есть ли заголовок на странице
                assertTrue(isElementPresent(By.cssSelector(locatorTitle)));

            }
        }

    }
}
