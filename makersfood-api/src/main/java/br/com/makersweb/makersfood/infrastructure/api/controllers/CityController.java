package br.com.makersweb.makersfood.infrastructure.api.controllers;

import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.infrastructure.api.CityAPI;
import br.com.makersweb.makersfood.infrastructure.city.models.CityListResponse;
import br.com.makersweb.makersfood.infrastructure.city.models.CityResponse;
import br.com.makersweb.makersfood.infrastructure.city.models.CreateCityRequest;
import br.com.makersweb.makersfood.infrastructure.city.models.UpdateCityRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aaristides
 */
@RestController
public class CityController implements CityAPI {

    @Override
    public ResponseEntity<?> createCity(CreateCityRequest input) {
        return null;
    }

    @Override
    public Pagination<CityListResponse> listCities(String search, int page, int perPage, String sort, String direction) {
        return null;
    }

    @Override
    public CityResponse getById(String id) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateById(String id, UpdateCityRequest input) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
