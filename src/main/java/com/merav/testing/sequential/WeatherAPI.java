package com.merav.testing.sequential;

import java.util.ArrayList;
import java.util.List;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.IsEqual.equalTo;

public class WeatherAPI {

    final private static Logger LOGGER = LoggerFactory.getLogger(WeatherAPI.class.getName());

    List<String> cities = new ArrayList<>();

    @BeforeTest
    public void setup() {
        initCities();
    }

    @Test
    public void printTempByCity() {
        for (String city : cities) {
            LOGGER.info("[" + city + "]:[" + getTempFromCity(city) + "]");
        }
    }

    private void initCities() {
        cities.add("Holon");
        cities.add("Tel Aviv");
        cities.add("Petah Tikva");
        cities.add("New York");

    }

    public static String getTempFromCity(String city) {

        RestAssured.baseURI = "http://api.openweathermap.org/data/2.5";

        Response response =
                RestAssured.
                        given().
                        param("q", city).
                        param("APPID", "b241b55576ead4eb73992820bda8b62f").
                        when().
                        get("/weather").
                        then().
                        contentType(JSON).
                        body("name", equalTo(city)).
                        extract().
                        response();

        String temp = response.path("main.temp").toString();
        return temp;
    }
}

