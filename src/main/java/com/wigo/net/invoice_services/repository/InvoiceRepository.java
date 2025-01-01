package com.wigo.net.invoice_services.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wigo.net.invoice_services.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    
    public void deleteById(Long id);
    
    public boolean existsById(Long id);

    public Optional<Invoice> findById(Long id);
}
