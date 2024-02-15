package com.elbh.plataforma.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.elbh.plataforma.Models.Categoria;
import com.elbh.plataforma.Models.DetalleCompletoDTO;
import com.elbh.plataforma.Models.Mesa;
import com.elbh.plataforma.Models.Plato;
import com.elbh.plataforma.Models.Producto;
import com.elbh.plataforma.Models.ProductoPostDTO;
import com.elbh.plataforma.Models.VPRequest;
import com.elbh.plataforma.Models.Venta;
import com.elbh.plataforma.Models.VentaProducto;
import com.elbh.plataforma.Repository.AtencionRepository;
import com.elbh.plataforma.Repository.PlatoRepository;
import com.elbh.plataforma.Repository.ProductoRepository;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentaWebService {
    private final ProductoRepository pRepo;
    private final PlatoRepository platoRepo;
    private final AtencionRepository atencionRepo;

    public List<Producto> getProductosIndex(Model model){
         List<Producto> elementos = pRepo.getProductos();
         return elementos;
    }

    public List<Plato> getPlatosIndex(){
        List<Plato> elementos = platoRepo.getPlatos();
        return elementos;
   }


    public List<Mesa> getMesas(){
        List<Mesa> elementos = atencionRepo.getMesas();
        return elementos;
    }

    public Venta createVenta(Venta venta){
        ResponseEntity<Venta> respuesta = pRepo.createVenta(venta);
        System.out.println(respuesta.getBody().toString());
        return respuesta.getBody();
    }

    public VentaProducto createVentaProducto(Integer idVenta, VPRequest vpRequest){
        ResponseEntity<VentaProducto> respuesta = pRepo.createVentaProducto(idVenta, vpRequest);
        return respuesta.getBody();
    }

    public Venta getVenta(Integer id){
        Venta venta = pRepo.getVenta(id);
        return venta;
    }

    public List<DetalleCompletoDTO> getDetalles(Integer id){
        List<DetalleCompletoDTO> detalles = pRepo.getDetalles(id);
        return detalles;
    }

    public List<Categoria> getCategorias(){
        List<Categoria> categorias = pRepo.getCategorias();
        return categorias;
    }

    public Producto createProducto(ProductoPostDTO producto){
        ResponseEntity<Producto> respuesta = pRepo.createProducto(producto);
        System.out.println(respuesta.getBody().toString());
        return respuesta.getBody();
    }

}
