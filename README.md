#Santander transaction history extractor

As a consumer of [Santander Bank Polska](https://www.santander.pl/), I have possibility to assign categories to all of my transactions.
This helps me monitor my expenses and see how much money I spend on items from different categories.

But the bank solution is not flexible enough. It doesn't have exporting function, reporting tools are very limited, transactions could not be splited into different categories (i.e. splitting supermarket purchase into categories _"alcohol"_ and _"food"_).

Furthermore, such functionality binds me to the bank, because I don't want to lose reports that I was maintaining for years. 

This project was created to extract all transfers with asssigned expense categories from my Santander Bank account to csv file using automated software.

After the categories will be exported, I am going to use other more flexible tools. The exporter can be used once for given period, and exported transactions could be imported into tool.


## Usage
Application is implemented using Spring Boot framework and Selenium tool.

### Requirements
Only Chrome Browser is compatible for this application.
**chromedriver** should be placed in root directory of project. Check the list of selenium [chrome drivers](https://chromedriver.chromium.org/downloads).

### Input
Next environment variables should be set:
* `SANTANDER_NIK` - NIK (Numer indywidualny klienta) of the user
* `SANTANDER_PASSWORD` - password of the user
* `SANTANDER_TRUST_TOKEN` - token of trusted device, which can be retrieved from local storage (centrum24).

### Running

```shell
mvn clean install
SANTANDER_PASSWORD="change_me" SANTANDER_NIK="change_me" SANTANDER_TRUST_TOKEN="change_me" java -jar target/SantanderTransactionHistoryExtractor-1.0.jar
```

##Troubleshooting
### Mac OS
To remove **chromedriver** from quarantine, use next command:


```sh
xattr -d com.apple.quarantine chromedriver
```

