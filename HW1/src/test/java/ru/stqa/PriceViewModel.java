package ru.stqa;

import org.openqa.selenium.WebElement;

import java.awt.*;

public class PriceViewModel {
    public ColorViewModel color;
    public String name;
    public String price;
    public String link;
    public boolean isFat;
    public String Class;
    public String tag;
    public Double fontSize;

    public PriceViewModel(WebElement element) {
        price = element.getAttribute("textContent");
        Class = element.getAttribute("class");
        tag = element.getAttribute("tagName");
        String elementColorStr = element.getCssValue("color");
        color = parseColor(elementColorStr);
    }

    private ColorViewModel parseColor(String colors){
        String[] numbers = colors.replace("rgba(", "").replace(")", "").split(",");
        int r = Integer.parseInt(numbers[0].trim());
        int g = Integer.parseInt(numbers[1].trim());
        int b = Integer.parseInt(numbers[2].trim());
        ColorViewModel col = new ColorViewModel();
        col.red = r;
        col.green = g;
        col.blue = b;
        return col;
    }
}
