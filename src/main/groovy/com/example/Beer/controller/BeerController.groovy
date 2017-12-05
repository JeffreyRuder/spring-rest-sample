package com.example.Beer.controller

import com.example.Beer.config.BeerNotFoundException
import com.example.Beer.domain.Beer
import com.example.Beer.repository.BeerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RestController
@RequestMapping("/beers")
class BeerController {

    private BeerRepository beerRepository

    @Autowired
    BeerController(BeerRepository beerRepository) {
        this.beerRepository = beerRepository
    }

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
