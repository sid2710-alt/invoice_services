package com.wigo.net.invoice_services.mapper;

import java.util.stream.Collectors;

import com.wigo.net.invoice_services.dto.InvoiceDto;
import com.wigo.net.invoice_services.dto.ItemDto;
import com.wigo.net.invoice_services.model.Invoice;
import com.wigo.net.invoice_services.model.Item;

public class InvoiceMapper {

    // Convert Invoice to InvoiceDTO
    public static InvoiceDto toDto(Invoice invoice) {
        return InvoiceDto.builder()
                .clientName(invoice.getClientName())
                .amount(invoice.getItems().stream()
                        .mapToDouble(item -> item.getQuantity() * item.getPrice())
                        .sum())
                .date(invoice.getDate())
                .items(invoice.getItems().stream()
                        .map(InvoiceMapper::toItemDto)
                        .collect(Collectors.toList()))
                .build();
    }

    // Convert Item to ItemDTO
    private static ItemDto toItemDto(Item item) {
        return ItemDto.builder()
                .name(item.getName())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .build();
    }

    // Convert InvoiceDTO to Invoice (optional)
    public static Invoice toEntity(InvoiceDto dto) {
        Invoice invoice = new Invoice();
        invoice.setClientName(dto.getClientName());
        invoice.setItems(dto.getItems().stream()
                .map(InvoiceMapper::toItemEntity)
                .collect(Collectors.toList()));
        return invoice;
    }

    // Convert ItemDTO to Item (optional)
    private static Item toItemEntity(ItemDto dto) {
        Item Item = new Item();
        Item.setName(dto.getName());
        Item.setQuantity(dto.getQuantity());
        Item.setPrice(dto.getPrice());
        return Item;
    }
}
