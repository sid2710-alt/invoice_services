package com.wigo.net.invoice_services.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ItemDto {
    private String name;
    private int quantity;
    private double price;
}
