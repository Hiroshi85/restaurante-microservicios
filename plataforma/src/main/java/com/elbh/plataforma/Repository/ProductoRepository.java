package com.elbh.plataforma.Repository;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.elbh.plataforma.Models.Categoria;
import com.elbh.plataforma.Models.DetalleCompletoDTO;
import com.elbh.plataforma.Models.Producto;
import com.elbh.plataforma.Models.ProductoPostDTO;
import com.elbh.plataforma.Models.VPRequest;
import com.elbh.plataforma.Models.Venta;
import com.elbh.plataforma.Models.VentaProducto;

import lombok.NoArgsConstructor;

@Repository
@NoArgsConstructor
public class ProductoRepository {
    public List<Producto> getProductos(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Producto>> rateResponse =
        restTemplate.exchange("http://localhost:8000/productos/",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Producto>>() {
            });

        List<Producto> listaProductos = rateResponse.getBody();
        return listaProductos;
    }

    public ResponseEntity<Venta> createVenta(Venta venta){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Venta> result = restTemplate.postForEntity("http://localhost:8000/ventas/", venta, Venta.class);
        return result;
    }

    public Venta getVenta(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Venta> response = restTemplate.getForEntity("http://localhost:8000/ventas/"+id, Venta.class);
        Venta venta = response.getBody();
        return venta;
    }

    public List<DetalleCompletoDTO> getDetalles(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<DetalleCompletoDTO>> rateResponse =
        restTemplate.exchange("http://localhost:8000/ventas/"+id+"/detalle",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<DetalleCompletoDTO>>() {
            });

        List<DetalleCompletoDTO> listaDetalles = rateResponse.getBody();
        return listaDetalles;
    }

    public ResponseEntity<VentaProducto> createVentaProducto(Integer idVenta, VPRequest vpRequest){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<VentaProducto> result = restTemplate.postForEntity("http://localhost:8000/ventas/"+idVenta+"/detalle", vpRequest, VentaProducto.class);
        return result;
    }

    public List<Categoria> getCategorias(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Categoria>> rateResponse =
        restTemplate.exchange("http://localhost:8000/productos/categorias",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Categoria>>() {
            });

        List<Categoria> listaCategorias = rateResponse.getBody();
        return listaCategorias;
    }

    public ResponseEntity<Producto> createProducto(ProductoPostDTO producto){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Producto> result = restTemplate.postForEntity("http://localhost:8000/productos/", producto, Producto.class);
        return result;
    }
}
