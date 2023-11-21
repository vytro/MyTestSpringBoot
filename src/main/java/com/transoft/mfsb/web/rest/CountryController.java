package com.transoft.mfsb.web.rest;

import com.transoft.mfsb.domain.entity.Country;
import com.transoft.mfsb.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

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

    @PostMapping
    public ResponseEntity<Country> saveCountry(@RequestBody final Country country) throws URISyntaxException {
        //if already found
        if(country.getId() != null){
            throw new IllegalArgumentException("A new Country cannot already have an id");
        }
        Country countryFromDB = countryService.save(country);
        return ResponseEntity.created(new URI("/v1/countries" + countryFromDB.getId())).body(countryFromDB);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> editCountry(@RequestBody final Country country,
                                               @PathVariable final Integer id) throws URISyntaxException {
        //if id is null
        if(country.getId() == null){
            throw new IllegalArgumentException("Country id cannot be null");
        }

        if(!Objects.equals(id, country.getId())){
            throw new IllegalArgumentException("Invalid Country id");
        }

//        Country countryFromDB = countryService.edit(country);
//        return ResponseEntity.ok().body(countryFromDB);

        return ResponseEntity
                .ok()
                .body(countryService.edit(country));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable final Integer id){
        countryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
