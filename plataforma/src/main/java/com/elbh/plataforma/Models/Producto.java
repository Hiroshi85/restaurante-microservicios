package com.elbh.plataforma.Models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    public Producto(String descripcion, Double precioUnitario, Timestamp createdAt, Categoria categoria){
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.createdAt = createdAt;
        this.categoria = categoria;
    }

    public Producto(String descripcion, Double precioUnitario, Integer idCategoria){
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.idCategoria = idCategoria;
    }

    private Integer id;
    private String descripcion;
    private Double precioUnitario;
    private Timestamp createdAt;
    private Integer idCategoria;
    private Categoria categoria;

    
}
