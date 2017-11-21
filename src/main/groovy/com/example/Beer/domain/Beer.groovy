package com.example.Beer.domain

import org.hibernate.annotations.GenericGenerator

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "beers")
class Beer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id

    @Column(name = "brand", updatable = true, nullable = true)
    private String brand

    @Column(name = "name", updatable = true, nullable = false)
    private String name

    @Column(name = "ibu", updatable = true, nullable = true)
    private Integer ibu

    Beer() {}

    Beer(String brand = null, String name, Integer ibu = null) {
        this.brand = brand
        this.name = name
        this.ibu = ibu
    }

    UUID getId() {
        return id
    }

    String getBrand() {
        return brand
    }

    void setBrand(String brand) {
        this.brand = brand
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    Integer getIbu() {
        return ibu
    }

    void setIbu(Integer ibu) {
        this.ibu = ibu
    }
}
