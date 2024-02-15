package com.elbh.plataforma.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleCompletoDTO {
    private Integer id;
    private Integer idVenta;
    private Integer cantidad;
    private Double precioUnitario;
    private Producto producto;   
}