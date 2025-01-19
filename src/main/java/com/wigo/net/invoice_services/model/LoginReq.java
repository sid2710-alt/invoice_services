package com.wigo.net.invoice_services.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginReq {

    private String username;
    private String password;

    // Getters and Setters
}
