package com.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transfer {
    private String date;
    private String receiver;
    private String title;
    private String amount;
    private String category;
}
