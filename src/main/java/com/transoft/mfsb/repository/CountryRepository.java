package com.transoft.mfsb.repository;

import com.transoft.mfsb.domain.entity.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, Integer> {
}




/*Here's a brief overview of some of the methods available from CrudRepository:
save(T entity): Save an entity to the database.
findById(ID id): Retrieve an entity by its id.
existsById(ID id): Check if an entity exists by its id.
findAll(): Retrieve all entities.
delete(T entity): Delete an entity from the database.
In this case, CountryRepository is a Spring Data JPA repository for Country entities.*/