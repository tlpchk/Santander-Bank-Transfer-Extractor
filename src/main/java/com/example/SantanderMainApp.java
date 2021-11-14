package com.example;

import com.example.exporter.CsvExporter;
import com.example.extractor.SantanderExtractor;
import com.example.navigator.SantanderBankNavigator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SantanderMainApp {
    public static void main(String[] args) {
        SpringApplication.run(SantanderMainApp.class, args);
    }

    @Bean
    CommandLineRunner santanderRunner(
            SantanderBankNavigator navigator,
            SantanderExtractor extractor,
            CsvExporter exporter
    ){
        return args -> {
            navigator.logIn();
            navigator.navigateTransferPage();
            var transfers = extractor.extractTransfers();
            exporter.export(transfers);
        };
    }
}
