package com.elbh.plataforma.Models;

import java.sql.Timestamp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plato {
    private Integer id;

    private String descripcion;
    private Double precio;

    private Timestamp createdAt;

    private Categoria categoria;
}
