package com.example.exporter;

import com.example.model.Transfer;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.Writer;
import java.util.List;

@Component
public class CsvExporter implements Exporter{
    @Value("${output-file}")
    private String fileName;

    @SneakyThrows
    @Override
    public void export(List<Transfer> transfers) {
        Writer writer = new FileWriter(fileName);
        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
        beanToCsv.write(transfers);
        writer.close();
    }
}
