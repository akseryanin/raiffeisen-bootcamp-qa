package ru.stqa;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HW_TASK5 extends TestBase {

    private boolean isSortedArray(List<String> array)
    {
        Boolean flag = true;
        int Len = array.size();
        for (int i = 1; i < Len; ++i)
            if (array.get(i).compareTo(array.get(i - 1)) < 0)
                flag = false;
        return flag;
    }
    @Test
    public void isCountiesSorted(){
        //авторизация
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        //sleep(500);

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        List<WebElement> rows = driver.findElements(By.cssSelector(".dataTable tr.row"));
        ArrayList<String> countries = new ArrayList<>();

        for (WebElement row : rows){
            String country = row.findElement(By.cssSelector("a")).getAttribute("textContent");
            countries.add(country);
        }
        //проверяем отсорчены ли страны по алфавиту
        assertTrue(isSortedArray(countries));

        for (int i = 2; i <= countries.size() + 1; ++i) {
            //страница будет обновляться, поэтому заново искать элементы надо
            String rowLocator = ".dataTable tr.row:nth-child("+i+")";
            WebElement row = driver.findElement(By.cssSelector(rowLocator));

            int countZones = Integer.parseInt(row.findElement(By.cssSelector("td:nth-child(6)")).getAttribute("textContent"));
            if (countZones > 0){
                row.findElement(By.cssSelector("a")).click();
                //sleep(500);
                List<WebElement> countryZones = driver.findElements(By.cssSelector(".dataTable td[type=hidden]"));
                ArrayList<String> zones = new ArrayList<>();
                for (WebElement zone : countryZones) {
                    String zoneName = zone.getAttribute("value");
                    zones.add(zoneName);
                }
                assertTrue(isSortedArray(zones));
                //возвращаемся обратно
                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
            }
        }
    }

    @Test
    public void isSortedZones() {
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        int countRows = driver.findElements(By.cssSelector(".dataTable tr.row")).size();
        for (int i = 2; i <= countRows + 1; ++i){
            String countryLocator = ".dataTable tr.row:nth-child("+i+")";
            WebElement row = driver.findElement(By.cssSelector(countryLocator));
            row.findElement(By.cssSelector("a")).click();
            List<WebElement> countryZones = driver.findElements(By.cssSelector(".dataTable [name*=zone_code]"));
            ArrayList<String> zones = new ArrayList<>();

            for (WebElement zone : countryZones){
                String zoneName = zone.findElement(By.cssSelector("[selected]")).getAttribute("textContent");
                zones.add(zoneName);
            }
            assertTrue(isSortedArray(zones));

            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        }
    }
}
