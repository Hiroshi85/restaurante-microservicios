package com.elbh.plataforma.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VPRequest {
    private Integer idProducto;
    private Integer cantidad;
}
