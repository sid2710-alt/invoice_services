package com.wigo.net.invoice_services.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private long expiresIn;
 // Getters and setters...
}