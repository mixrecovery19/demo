package com.bit235.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bit235.model.Country;
// JpaRepository is a Spring Data interface that provides the CRUD operations for the, in this case, the Country entiry.
// to add to the Repository layer description, we could also apply this inside the CountrollerController class if we wished to
// and not have the Repositroy layer separated, however it is good practise, easier to read and far more scalable to have separate layers 
// broken up into their own classes/folders.
//*** I KNOW THE ABOVE IS BAD PRACTICE I JUST WANTED TO POINT OUT THAT IS HOW WE STARTED LEARNING OUR PROJECTS ****/
public interface CountryRepository extends JpaRepository<Country, Long> {
}