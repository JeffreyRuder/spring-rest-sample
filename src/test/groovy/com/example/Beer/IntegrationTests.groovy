package com.example.Beer

import com.example.Beer.domain.Beer
import io.restassured.RestAssured
import io.restassured.response.Response
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric
import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue


@RunWith(SpringRunner)
@SpringBootTest(classes = [BeerApplication.class], webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class IntegrationTests {

    private static final String API_ROOT = "http://localhost:8080/beers"

    private static Beer createRandomBeer() {
        new Beer(randomAlphabetic(8), randomAlphabetic(10), Integer.parseInt(randomNumeric(3)))
    }

    private static String saveBeer(Beer beer) {
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(beer)
                .post(API_ROOT)
        return API_ROOT + "/" + response.jsonPath().get("id")
    }

    @Test
    void whenCreate_thenCreated() {
        Beer beer = createRandomBeer()
        Response response = RestAssured.given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(beer)
            .post(API_ROOT)

        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode())
    }

    @Test
    void whenInvalidBeer_thenError() {
        Beer beer = createRandomBeer()
        beer.setName(null)
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(beer)
                .post(API_ROOT)

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode())
    }

    @Test
    void whenGetAll_thenOk() {
        Response response = RestAssured.get(API_ROOT)
        assertEquals(HttpStatus.OK.value(), response.getStatusCode())
    }

    @Test
    void whenGetById_thenOk() {
        Beer beer = createRandomBeer()
        String url = saveBeer(beer)
        Response response = RestAssured.get(url)
        assertEquals(HttpStatus.OK.value(), response.getStatusCode())
        assertEquals(beer.getName(), response.jsonPath().get("name"))
    }

    @Test
    void whenGetByIdWithInvalidUUID_thenBadRequest() {
        Response response = RestAssured.get(API_ROOT + "/" + randomAlphabetic(4))
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode())
    }

    @Test
    void whenGetByIdNotExists_thenNotFound() {
        Response response = RestAssured.get(API_ROOT + "/" + UUID.randomUUID().toString())
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode())
    }

    @Test
    void whenGetByBrand_thenOk() {
        Beer beer = createRandomBeer()
        saveBeer(beer)
        Response response = RestAssured.get(
                API_ROOT + "/brand/" + beer.getBrand())
        assertEquals(HttpStatus.OK.value(), response.getStatusCode())
        assertTrue(response.as(List.class).size() > 0)
    }

    @Test
    void whenGetByName_thenOk() {
        Beer beer = createRandomBeer()
        saveBeer(beer)
        Response response = RestAssured.get(
                API_ROOT + "/name/" + beer.getName())
        assertEquals(HttpStatus.OK.value(), response.getStatusCode())
        assertTrue(response.as(List.class).size() > 0)
    }

    @Test
    void whenGetByIBU_thenOk() {
        Beer beer = createRandomBeer()
        saveBeer(beer)
        Response response = RestAssured.get(
                API_ROOT + "/ibu/" + beer.getIbu())
        assertEquals(HttpStatus.OK.value(), response.getStatusCode())
        assertTrue(response.as(List.class).size() > 0)
    }
}
