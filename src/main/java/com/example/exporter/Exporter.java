package com.example.exporter;

import com.example.model.Transfer;

import java.util.List;

public interface Exporter {
    void export(List<Transfer> transfers);
}
