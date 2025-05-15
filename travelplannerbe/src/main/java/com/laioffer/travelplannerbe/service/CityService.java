package com.laioffer.travelplannerbe.service;

import com.laioffer.travelplannerbe.model.CityDTO;
import com.laioffer.travelplannerbe.model.CityEntity;
import com.laioffer.travelplannerbe.repository.CityRepository;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    // use cityId to get cityName and location
    public CityDTO getCityNameAndLocation(long cityId) {
        CityEntity cityEntity = cityRepository.getById(cityId);
        return CityDTO.fromCityEntity(cityEntity);
    }
}
