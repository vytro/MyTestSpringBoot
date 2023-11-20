package com.transoft.mfsb.service;

import com.transoft.mfsb.domain.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listCountry();
    Optional<Country> getCountryById(Integer id);
}