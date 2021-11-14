package com.example.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class SeleniumUtils {

    public static WebElement waitForPresence(WebDriver driver, By by, int waitTimeout) {
        var wait = new WebDriverWait(driver, Duration.ofMillis(waitTimeout));
        wait.until(presenceOfElementLocated(by));
        return driver.findElement(by);
    }

    public static WebElement waitForClickable(WebDriver driver, By by, int waitTimeout) {
        var wait = new WebDriverWait(driver, Duration.ofMillis(waitTimeout));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        return driver.findElement(by);
    }

    public static void waitForInvisible(WebDriver driver, By by, int waitTimeout) {
        var wait = new WebDriverWait(driver, Duration.ofMillis(waitTimeout));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}
