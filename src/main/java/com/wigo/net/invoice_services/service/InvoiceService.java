package com.wigo.net.invoice_services.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wigo.net.invoice_services.dto.InvoiceDto;
import com.wigo.net.invoice_services.exception.ResourceNotFoundException;
import com.wigo.net.invoice_services.mapper.InvoiceMapper;
import com.wigo.net.invoice_services.model.Invoice;
import com.wigo.net.invoice_services.repository.InvoiceRepository;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

  
    public Long createInvoice(InvoiceDto invoiceDto) {
        Invoice invoice = InvoiceMapper.toEntity(invoiceDto);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return savedInvoice.getId();
    }

    public Long updateInvoice(Long id, InvoiceDto updatedInvoiceDto) {
        Invoice existingInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with ID: " + id));
                Invoice updatedInvoice = InvoiceMapper.toEntity(updatedInvoiceDto);
        
        existingInvoice.setClientName(updatedInvoice.getClientName());
        existingInvoice.setDate(updatedInvoice.getDate());
        existingInvoice.setItems(updatedInvoice.getItems());
        existingInvoice.setTotalAmount(updatedInvoice.getTotalAmount());

        Invoice savedInvoice = invoiceRepository.save(existingInvoice);
        return savedInvoice.getId();
    }

    public List<InvoiceDto> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream()
                .map(InvoiceMapper::toDto)
                .collect(Collectors.toList());
    }

    public InvoiceDto getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id)) ;
                
        return InvoiceMapper.toDto(invoice);
    }
    
    public void deleteInvoice(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Invoice not found with id: " + id);
        }
        invoiceRepository.deleteById(id);
    }
}
