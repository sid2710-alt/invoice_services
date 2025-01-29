package com.wigo.net.invoice_services.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoices")
@Getter
@Setter
public class Invoice {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id") // Explicit column name
    private Long id;

    @Column(name = "client_name", nullable = false, length = 100) // Non-null, length restriction
    private String clientName;

    @Temporal(TemporalType.DATE)
    @Column(name = "invoice_date") // Explicit column name for date
    private Date date;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="invoice") // Foreign key column in `items` table
    private List<Item> items;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    public void addItems(List<Item> items) {
        this.items.addAll(items);
    }
    public void clearItems() {
        this.items.clear();
    }
}

