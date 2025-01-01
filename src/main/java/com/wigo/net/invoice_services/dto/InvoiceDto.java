package com.wigo.net.invoice_services.dto;


import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class InvoiceDto {
    private String clientName;
    private double amount; // Calculated from items
    private Date date;
    private List<ItemDto> items;
}
