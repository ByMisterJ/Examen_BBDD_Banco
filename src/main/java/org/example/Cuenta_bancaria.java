package org.example;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Data
public class Cuenta_bancaria {
    private int id_cuenta;
    private String iban;
    private double balance;
    private int id_cliente;
}
