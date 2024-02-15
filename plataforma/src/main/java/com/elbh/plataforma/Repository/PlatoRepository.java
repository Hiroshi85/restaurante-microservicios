package com.elbh.plataforma.Repository;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.elbh.plataforma.Models.Plato;

import lombok.NoArgsConstructor;

@Repository
@NoArgsConstructor
public class PlatoRepository {
    public List<Plato> getPlatos(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Plato>> rateResponse =
        restTemplate.exchange("http://localhost:8000/comida/platos",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Plato>>() {
            });

        List<Plato> listaProductos = rateResponse.getBody();
        return listaProductos;
    }
}
