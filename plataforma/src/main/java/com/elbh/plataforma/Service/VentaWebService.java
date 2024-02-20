package com.elbh.plataforma.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.elbh.plataforma.Models.Categoria;
import com.elbh.plataforma.Models.Mesa;
import com.elbh.plataforma.Models.Pedido;
import com.elbh.plataforma.Models.PedidoConDetalle;
import com.elbh.plataforma.Models.Plato;
import com.elbh.plataforma.Models.PlatoRequestDTO;

import com.elbh.plataforma.Repository.AtencionRepository;
import com.elbh.plataforma.Repository.PlatoRepository;
import com.elbh.plataforma.Repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentaWebService {
    private final ProductoRepository pRepo;
    private final PlatoRepository platoRepo;
    private final AtencionRepository atencionRepo;

    public List<Plato> getPlatosIndex(){
        List<Plato> elementos = platoRepo.getPlatos();
        return elementos;
   }

    public List<Mesa> getMesas(){
        List<Mesa> elementos = atencionRepo.getMesas();
        return elementos;
    }

    public List<Pedido> listarPedidos(){
        List<Pedido> elementos = pRepo.getPedidos();
        return elementos;
    }

    public PedidoConDetalle createPedidoConDetalle(PedidoConDetalle pd){
        ResponseEntity<PedidoConDetalle> respuesta = pRepo.createPedidoConDetalle(pd);
        return respuesta.getBody();
    }

    public PedidoConDetalle getPedidoConDetalle(Integer id){
        ResponseEntity<PedidoConDetalle> respuesta = pRepo.getPedidoConDetalle(id);
        return respuesta.getBody();
    }

    public List<Categoria> getCategorias(){
        List<Categoria> categorias = pRepo.getCategorias();
        return categorias;
    }

    public Plato createPlato(PlatoRequestDTO plato){
        ResponseEntity<Plato> respuesta = pRepo.createPlato(plato);
        System.out.println(respuesta.getBody().toString());
        return respuesta.getBody();
    }

    public Mesa createMesa(Mesa mesa){
        ResponseEntity<Mesa> respuesta = atencionRepo.createMesa(mesa);
        return respuesta.getBody();
    }
}
