package com.wigo.net.invoice_services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wigo.net.invoice_services.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
