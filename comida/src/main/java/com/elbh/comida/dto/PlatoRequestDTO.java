package com.elbh.comida.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlatoRequestDTO {
    private String descripcion;
    private Double precio;
    private Integer idCategoria;
}
