package com.elbh.atencion.repositories.external;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.elbh.atencion.Dto.DetallePedido;

@Repository
public class DetallePedidoRepository {
    public List<DetallePedido> saveAll(List<DetallePedido> detalles){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<List<DetallePedido>> request = new HttpEntity<>(detalles);
        ResponseEntity<List<DetallePedido>> result = restTemplate.exchange("http://localhost:8092/all", HttpMethod.POST, request , new ParameterizedTypeReference<List<DetallePedido>>() {});   
        if(!result.getStatusCode().is2xxSuccessful()){
            return null;
        }
        return result.getBody();
    }

    public List<DetallePedido> getByIdPedido(Integer idPedido){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<DetallePedido>> result = restTemplate.exchange("http://localhost:8092/pedido/"+idPedido, HttpMethod.GET, null , new ParameterizedTypeReference<List<DetallePedido>>() {});   
        if(!result.getStatusCode().is2xxSuccessful()){
            return null;
        }
        return result.getBody();
    }
}
