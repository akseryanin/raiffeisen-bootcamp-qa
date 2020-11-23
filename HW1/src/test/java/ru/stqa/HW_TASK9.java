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
    public void bucketAddproductsTest() throws InterruptedException {
        driver.get("https://litecart.stqa.ru/en/");
        int nGoodsToAdd = 3;
        addNGoodsTotheBucket(nGoodsToAdd);
        delAllGoodsFromBucket();
    }

    private void addNGoodsTotheBucket(int nGoodsToAdd) {
/*
        1) открыть страницу какого-нибудь товара
        2) добавить его в корзину
        3) подождать, пока счётчик товаров в корзине обновится
        4) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара
*/

        for(int nGood = 0; nGood < nGoodsToAdd; nGood++) {

            //wail till more than nGoodsToAdd appear on page
            By mostPopularGoodsLocator = By.cssSelector("div#box-most-popular > div.content li");
            List<WebElement> mostPopularGoods = wait.until(numberOfElementsToBeMoreThan(mostPopularGoodsLocator, nGoodsToAdd));


            if (mostPopularGoods != null) {
                int ind = (int)(Math.round(Math.random() * (mostPopularGoods.size() - 1)));
                String name = mostPopularGoods.get(ind).findElement(By.cssSelector(".name")).getAttribute("textContent");
                //select random one of available goods and click
                mostPopularGoods
                        .get(ind)
                        .findElement(By.cssSelector("a.link"))
                        .click();
                wait.until(titleContains(name));

                //add to cart
                //check for required fields, by default lets choose first item
                By requiredFieldsLocator = By.cssSelector("form[name=buy_now_form] select[required=required]");
                List<WebElement> requiredFields = driver.findElements(requiredFieldsLocator);
                if (requiredFields.size() > 0) {
                    for(WebElement oneSelect : requiredFields){
                        oneSelect.sendKeys(Keys.ENTER);sleep(500);
                        oneSelect.sendKeys(Keys.ARROW_DOWN);
                        oneSelect.sendKeys(Keys.ENTER);
                    }

                }

                By cartSubmitButtonLocator = By.cssSelector("form[name=buy_now_form] button[type=submit]");
                wait.until(elementToBeClickable(cartSubmitButtonLocator));
                driver.findElement(cartSubmitButtonLocator).click();

                //wait till cart goods number changed
                By cartNGoodsLocator = By.cssSelector("div#cart a.content span.quantity");
                wait.until(attributeToBe(cartNGoodsLocator, "textContent", Integer.toString(nGood + 1)));
            } else {
                assertTrue("There are no goods in list!", mostPopularGoods != null);
            }
            sleep(500);

            //call page with goods
            driver.get("https://litecart.stqa.ru/en/");
        }

    }

    private void delAllGoodsFromBucket() throws InterruptedException {
/*
        5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
        6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица
*/

        //sleep(500);
        // click on cart
        By cartLocator = By.cssSelector("div#cart a.link:last-child");
        wait.until(titleContains("Checkout"));
        driver.findElement(cartLocator).click();

        sleep(500);
        By removeButtonsLocator = By.cssSelector("div.viewport button[name=remove_cart_item]");
        List<WebElement> removeButtonsList = wait.until(numberOfElementsToBeMoreThan(removeButtonsLocator, 0));
        int nGoodsToDel = removeButtonsList.size();

        while(nGoodsToDel > 0) {
            removeButtonsList = wait.until(numberOfElementsToBe(removeButtonsLocator, nGoodsToDel));
            if (removeButtonsList != null) {
                try {
                    wait.until(elementToBeClickable(removeButtonsLocator)).click();
                    nGoodsToDel--;
                } catch (Throwable e) {
                    //seems to be no more elements here =)
                }
            } else {
                nGoodsToDel = 0;
            }
        }

        //call initial page with goods
        driver.get("https://litecart.stqa.ru/en/");
    }
}
