package com.elbh.comida.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlatoResponse {
    private Integer id;

    private String descripcion;
    private Double precio;

    private Timestamp createdAt;
    private String categoria;
}
