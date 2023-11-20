package com.transoft.mfsb.web.rest;

import com.transoft.mfsb.domain.entity.Country;
import com.transoft.mfsb.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//  /v1/countries
@RestController
@RequestMapping("/v1/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<Country>> getCountries(){
        return ResponseEntity.ok().body(countryService.listCountry());
    }

    // /v1/countries/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable final Integer id){
        return ResponseEntity
                .ok(countryService.getCountryById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid country Id:" + id)));
    }
}
