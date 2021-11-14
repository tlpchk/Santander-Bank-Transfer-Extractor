package com.example.extractor;

import com.beust.jcommander.internal.Lists;
import com.example.model.Transfer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.utils.SeleniumUtils.*;

@Component
public class SantanderExtractor implements TransferExtractor {
    @Value("${wait-timeout}")
    private int waitTimeout;

    private final WebDriver driver;

    public SantanderExtractor(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public List<Transfer> extractTransfers() {
        WebElement nextPageButton;
        List<Transfer> allTransfers = Lists.newArrayList();
        while (true) {
            waitForInvisible(driver, By.className("loading-data-background"), waitTimeout);
            nextPageButton = waitForClickable(driver, By.xpath("//li[@class='nastepne']"), waitTimeout);
            allTransfers.addAll(getTransfersFromCurrentTable());
            if (nextPageButton.getText().isEmpty()) {
                break;
            }
            nextPageButton.click();
        }
        return allTransfers;
    }

    private List<Transfer> getTransfersFromCurrentTable() {
        WebElement table = waitForPresence(driver, new By.ById("transaction-table"), waitTimeout);
        List<WebElement> tableRows = table.findElements(By.xpath("//table/tbody/tr"));
        return tableRows.stream()
                .filter(tr -> tr.getAttribute("class").isEmpty())
                .map(tr -> Transfer.builder()
                        .date(getTableDataByClasname(tr, "date"))
                        .receiver(getTableDataByClasname(tr, "name oth-name"))
                        .title(getTableDataByClasname(tr, "name"))
                        .amount(getTableDataByClasname(tr, "amount"))
                        .category(getTableDataByClasname(tr, "category unhoverable"))
                        .build()
                ).collect(Collectors.toList());
    }

    private String getTableDataByClasname(WebElement tr, String className) {
        return tr.findElement(new By.ByXPath(String.format("td[@class='%s']", className))).getText();
    }
}
