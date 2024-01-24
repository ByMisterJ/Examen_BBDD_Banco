package org.example;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Data

public class Cliente {

    private int id_cliente;
    private String nombre;
    private String apellido;
    private int telefono;
    private String usuario;
    private String contrasenya;
    private String dni;
    private String email;
    private String nacionalidad;
    private String fecha_nacimiento;
    private String calle;
    private int cp;
    private String municipio;
    private String provincia;

    public Cliente(int id_cliente, String nombre) {
        this.id_cliente = id_cliente;
        this.nombre= nombre;
    }
}
