package com.wigo.net.invoice_services.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wigo.net.invoice_services.dto.InvoiceDto;
import com.wigo.net.invoice_services.exception.ResourceNotFoundException;
import com.wigo.net.invoice_services.mapper.InvoiceMapper;
import com.wigo.net.invoice_services.model.Invoice;
import com.wigo.net.invoice_services.model.User;
import com.wigo.net.invoice_services.repository.InvoiceRepository;
import com.wigo.net.invoice_services.repository.UserRepository;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, UserRepository userRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Long createInvoice(InvoiceDto invoiceDto, User user) {
        Invoice invoice = InvoiceMapper.toEntity(invoiceDto);
        invoice.setUser(user);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return savedInvoice.getId();
    }

    public Long updateInvoice(Long id, InvoiceDto updatedInvoiceDto) {
        Invoice existingInvoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with ID: " + id));
        Invoice updatedInvoice = InvoiceMapper.toEntity(updatedInvoiceDto);

        existingInvoice.setClientName(updatedInvoice.getClientName());
        existingInvoice.setDate(updatedInvoice.getDate());
        existingInvoice.clearItems(); // Clear the existing items
        existingInvoice.addItems(updatedInvoice.getItems());
        existingInvoice.setTotalAmount(updatedInvoice.getTotalAmount());

        Invoice savedInvoice = invoiceRepository.save(existingInvoice);
        return savedInvoice.getId();
    }

    public List<InvoiceDto> getAllInvoices(User user) {
        List<Invoice> invoices = invoiceRepository.findInvoicesByUserId(user.getId());
        return invoices.stream()
                .map(InvoiceMapper::toDto)
                .collect(Collectors.toList());
    }

    public InvoiceDto getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Invoice not found with id: " + id));

        return InvoiceMapper.toDto(invoice);
    }

    public void deleteInvoice(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Invoice not found with id: " + id);
        }
        invoiceRepository.deleteById(id);
    }
}
