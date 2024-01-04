package com.elbh.atencion.Dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoConDetalle {
    private Integer id;
    private Integer idMesa;
    private Integer mesa;
    private Timestamp createdAt;
    private List<DetallePedido> detalles;

    public PedidoConDetalle(Timestamp createdAt){
        this.createdAt = createdAt;
    }
}
