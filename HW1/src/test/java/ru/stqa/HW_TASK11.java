package ru.stqa;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class HW_TASK11 extends TestBase{
    @Test
    public void checkLogs(){

        loginAdmin();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        int rows = driver.findElements(By.cssSelector(".dataTable tr.row")).size();

        ArrayList<LogEntry> logs = new ArrayList<>();

        for (int i = 3; i <= rows + 1; ++i){

            WebElement row =  driver.findElement(By.cssSelector(".dataTable .row:nth-child("+i+")"));
            String checkboxName = row.findElement(By.tagName("input")).getAttribute("name");

            if (checkboxName.contains("products")){
                row.findElement(By.tagName("a")).click();
                logs.addAll(driver.manage().logs().get("browser").getAll());
            }
            assertEquals(0, logs.size());
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        }
    }
}
