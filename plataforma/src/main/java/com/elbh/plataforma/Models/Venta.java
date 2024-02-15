package com.elbh.plataforma.Models;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    public Venta(String comprador){
        this.comprador = comprador;
    }

    private Integer id;
    private String comprador;
    private Timestamp createdAt;
}