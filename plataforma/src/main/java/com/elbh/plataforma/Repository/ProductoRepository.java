package com.elbh.plataforma.Repository;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.elbh.plataforma.Models.Categoria;
import com.elbh.plataforma.Models.Mesa;
import com.elbh.plataforma.Models.Pedido;
import com.elbh.plataforma.Models.PedidoConDetalle;
import com.elbh.plataforma.Models.Plato;
import com.elbh.plataforma.Models.PlatoRequestDTO;

import lombok.NoArgsConstructor;

@Repository
@NoArgsConstructor
public class ProductoRepository {

    public ResponseEntity<PedidoConDetalle> createPedidoConDetalle(PedidoConDetalle pd){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PedidoConDetalle> result = restTemplate.postForEntity("http://localhost:8000/atencion/", pd, PedidoConDetalle.class);
        return result;
    }

    public ResponseEntity<PedidoConDetalle> getPedidoConDetalle(Integer id){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PedidoConDetalle> response = restTemplate.getForEntity("http://localhost:8000/atencion/"+id, PedidoConDetalle.class);
        return response;
    }

    public List<Pedido> getPedidos(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Pedido>> rateResponse =
        restTemplate.exchange("http://localhost:8000/atencion/",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Pedido>>() {
            });

        List<Pedido> listaPedidos = rateResponse.getBody();
        return listaPedidos;
    }

    public List<Categoria> getCategorias(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Categoria>> rateResponse =
        restTemplate.exchange("http://localhost:8000/comida/categorias",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Categoria>>() {
            });

        List<Categoria> listaCategorias = rateResponse.getBody();
        return listaCategorias;
    }

    public ResponseEntity<Plato> createPlato(PlatoRequestDTO plato){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Plato> result = restTemplate.postForEntity("http://localhost:8000/comida/platos", plato, Plato.class);
        return result;
    }

}
