package com.wigo.net.invoice_services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wigo.net.invoice_services.dto.InvoiceDto;
import com.wigo.net.invoice_services.model.User;
import com.wigo.net.invoice_services.service.InvoiceService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
 
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getAllInvoices() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(invoiceService.getAllInvoices(currentUser)); // HTTP 200 OK with body
    }

    // Get invoice by ID
    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable Long id) {
        InvoiceDto invoiceDto = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoiceDto); // HTTP 200 OK with body
    }

    // Create a new invoice
    @PostMapping
    public ResponseEntity<Long> createInvoice(@RequestBody InvoiceDto invoice) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.createInvoice(invoice, currentUser)); // HTTP 201 Created with body
    }

    // Update an invoice
    @PutMapping("/{id}")
    public ResponseEntity<Long> updateInvoice(@PathVariable Long id, @RequestBody InvoiceDto updatedInvoice) {
        return ResponseEntity.ok(invoiceService.updateInvoice(id, updatedInvoice)); // HTTP 200 OK with body
    }

    // Delete an invoice
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}
