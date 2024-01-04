package com.elbh.atencion.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetallePedido {
    private Integer id;
    private Integer idPedido;
    private Integer idPlato;
    private String plato;
    private Integer cantidad;
    private Double precio;
}
