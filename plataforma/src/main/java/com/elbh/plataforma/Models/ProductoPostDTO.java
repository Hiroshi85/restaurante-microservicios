package com.elbh.plataforma.Models;

import lombok.Value;

@Value
public class ProductoPostDTO {
    private String descripcion;
    private Double precioUnitario;
    private Integer idCategoria;
}

