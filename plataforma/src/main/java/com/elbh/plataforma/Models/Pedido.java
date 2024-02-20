package com.elbh.plataforma.Models;

import java.sql.Timestamp;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    private Integer id;

    private Mesa mesa;

    private Timestamp createdAt;
}
