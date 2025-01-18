package com.wigo.net.invoice_services.dto;

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
public class ItemDto {
    private long id;
    private String name;
    private int quantity;
    private double price;
    private String description;
}
