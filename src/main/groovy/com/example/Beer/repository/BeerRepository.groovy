package com.example.Beer.repository

import com.example.Beer.domain.Beer
import org.springframework.data.repository.CrudRepository

import javax.transaction.Transactional

@Transactional
interface BeerRepository extends CrudRepository<Beer, UUID> {

    Optional<Beer> findOne(UUID uuid)

    List<Beer> findByBrand(String brand)

    List<Beer> findByName(String name)

    List<Beer> findByIbu(Integer ibu)

}
