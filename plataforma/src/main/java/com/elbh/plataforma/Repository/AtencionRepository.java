package com.elbh.plataforma.Repository;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.elbh.plataforma.Models.Mesa;

import lombok.NoArgsConstructor;

@Repository
@NoArgsConstructor
public class AtencionRepository {
    public List<Mesa> getMesas(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Mesa>> rateResponse =
        restTemplate.exchange("http://localhost:8000/atencion/mesas",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Mesa>>() {
            });

        List<Mesa> listaProductos = rateResponse.getBody();
        return listaProductos;
    }
}
