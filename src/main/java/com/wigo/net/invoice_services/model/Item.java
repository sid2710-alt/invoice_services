package com.wigo.net.invoice_services.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id") // Explicit column name
    private Long id;

    @Column(name = "item_name", nullable = false, length = 100) // Non-null with max length
    private String name;

    @Column(name = "quantity", nullable = false) // Non-null quantity
    private int quantity;

    @Column(name = "price", nullable = false) // Non-null price
    private double price;
}

