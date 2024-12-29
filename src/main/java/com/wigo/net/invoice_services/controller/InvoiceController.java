package com.wigo.net.invoice_services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wigo.net.invoice_services.model.Invoice;
import com.wigo.net.invoice_services.repository.InvoiceRepository;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return ResponseEntity.ok(invoices); // HTTP 200 OK with body
    }

    // Get invoice by ID
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with ID: " + id));
        return ResponseEntity.ok(invoice); // HTTP 200 OK with body
    }

    // Create a new invoice
    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInvoice); // HTTP 201 Created with body
    }

    // Update an invoice
    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice updatedInvoice) {
        Invoice existingInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with ID: " + id));
        
        existingInvoice.setClientName(updatedInvoice.getClientName());
        existingInvoice.setDate(updatedInvoice.getDate());
        existingInvoice.setItems(updatedInvoice.getItems());
        existingInvoice.setTotalAmount(updatedInvoice.getTotalAmount());

        Invoice savedInvoice = invoiceRepository.save(existingInvoice);
        return ResponseEntity.ok(savedInvoice); // HTTP 200 OK with body
    }

    // Delete an invoice
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with ID: " + id));
        
        invoiceRepository.delete(invoice);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

    private static class ResourceNotFoundException extends Invoice {

        public ResourceNotFoundException(String string) {
        }
    }
}
