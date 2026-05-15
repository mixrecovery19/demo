package com.bit235.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit235.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
}