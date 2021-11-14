package com.example.navigator;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.WebStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

import static com.example.utils.SeleniumUtils.waitForClickable;
import static com.example.utils.SeleniumUtils.waitForPresence;

@Component
@Profile("santander")
public class SantanderBankNavigator implements BankNavigator {

    @Value("${url.login}")
    private String loginUrl;
    @Value("${wait-timeout}")
    private int waitTimeout;
    @Value("${user.nik}")
    private String nik;
    @Value("${user.password}")
    private String password;
    @Value("${user.trust-token}")
    private String trustToken;
    @Value("${url.transfers}")
    private String transfersUrl;

    private final WebDriver driver;

    public SantanderBankNavigator(WebDriver driver) {
        this.driver = driver;
    }

    @SneakyThrows
    @Override
    public void logIn() {
        driver.get(loginUrl);
        this.hackTrustedDeviceLogic();
        this.acceptCookies();
        this.typeNIK();
        this.typePassword();
    }

    private void hackTrustedDeviceLogic() {
        driver.manage().deleteAllCookies();
        ((WebStorage) driver).getSessionStorage().clear();
        ((WebStorage) driver).getLocalStorage().setItem("centrum24", trustToken);
        driver.navigate().refresh();
    }

    private void acceptCookies() {
        var acceptButton = waitForPresence(driver, new By.ById("privacy-prompt-controls-button-accept"), waitTimeout);
        acceptButton.click();
    }

    private void typeNIK() {
        WebElement nikInput = waitForPresence(driver, new By.ById("input_nik"), waitTimeout);
        nikInput.sendKeys(nik);
        WebElement submit = waitForClickable(driver, new By.ById("okBtn2"), waitTimeout);
        submit.click();
    }

    private void typePassword() {
        WebElement passInput = waitForPresence(driver, new By.ById("ordinarypin"), waitTimeout);
        passInput.sendKeys(password);
        WebElement submit = waitForClickable(driver, new By.ById("okBtn2"), waitTimeout);
        submit.click();
    }

    @Override
    public void navigateTransferPage() {
        driver.get(transfersUrl);
    }

    @PreDestroy
    public void destroy() {
        driver.quit();
        driver.close();
    }
}
