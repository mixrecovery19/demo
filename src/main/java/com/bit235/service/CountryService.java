package com.bit235.service;
import java.util.List;
import org.springframework.stereotype.Service;
import com.bit235.model.Country;
import com.bit235.repository.CountryRepository;


@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getAllCountries() {
            return countryRepository.findAll();
        }
    }