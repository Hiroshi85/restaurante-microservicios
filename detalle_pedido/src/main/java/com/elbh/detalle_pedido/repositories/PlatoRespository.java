package com.elbh.detalle_pedido.repositories;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.elbh.detalle_pedido.entidades.models.PlatoResponse;

public class PlatoRespository {
    public PlatoResponse findById(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PlatoResponse> response
          = restTemplate.getForEntity("http://localhost:8092/platos/"+id, PlatoResponse.class);
        if(response.getStatusCode() != HttpStatus.OK){
            return null;
        }
        PlatoResponse p = response.getBody();
        return p;
    }
}
