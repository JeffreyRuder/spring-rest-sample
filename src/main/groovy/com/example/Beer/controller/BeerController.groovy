package com.example.Beer.controller

import com.example.Beer.domain.Beer
import com.example.Beer.config.BeerNotFoundException
import com.example.Beer.repository.BeerRepository
import io.swagger.annotations.ApiModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Controller
@RestController
@RequestMapping("/beers")
class BeerController {

    @Autowired
    private BeerRepository beerRepository

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Beer create(@RequestBody Beer beer) {
        beerRepository.save(beer)
    }

    @GetMapping
    Iterable<Beer> findAll() {
        beerRepository.findAll()
    }

    @GetMapping("/{uuid}")
    Beer findById(@PathVariable UUID uuid) {
        beerRepository.findOne(uuid).orElseThrow({new BeerNotFoundException()})
    }

    @GetMapping("/brand/{brand}")
    List<Beer> findByIbu(@PathVariable String brand) {
        beerRepository.findByBrand(brand)
    }

    @GetMapping("/name/{name}")
    List<Beer> findByName(@PathVariable String name) {
        beerRepository.findByName(name)
    }

    @GetMapping("/ibu/{ibu}")
    List<Beer> findByIbu(@PathVariable Integer ibu) {
        beerRepository.findByIbu(ibu)
    }

    @PutMapping("/{uuid}")
    Beer updateBeer(@RequestBody Beer beer, @PathVariable UUID uuid) {
        if (beer.getId() != uuid) {
            throw new DataIntegrityViolationException("UUID values in body and path must match")
        }
        beerRepository.findOne(uuid).orElseThrow({new BeerNotFoundException()})
        beerRepository.save(beer)
    }

    @DeleteMapping("/{uuid}")
    void delete(@PathVariable UUID uuid) {
        beerRepository.findOne(uuid).orElseThrow({new BeerNotFoundException()})
        beerRepository.delete(uuid)
    }

}
