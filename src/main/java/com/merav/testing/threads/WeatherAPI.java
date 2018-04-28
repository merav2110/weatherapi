package com.merav.testing.threads;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.IsEqual.equalTo;

public class WeatherAPI implements Runnable {

    final private static Logger LOGGER = LoggerFactory.getLogger(WeatherAPI.class.getName());
    private Thread t;
    private String threadName;

    private String cityName; //input
    private static Map<String, String> results = new HashMap<>(); //results list of city & temp

     public void printResults(){
        LOGGER.info("printing results");
        for(String key : results.keySet()){
            LOGGER.info("["+key+"]["+results.get(key)+"]");
        }
    }
    public String getTempFromCity(String city){

        RestAssured.baseURI="http://api.openweathermap.org/data/2.5";
        //api.openweathermap.org/data/2.5/weather?q=London&APPID=b241b55576ead4eb73992820bda8b62f
        Response response =
                RestAssured.
                        given().
                        param("q",city).
                        param("APPID","b241b55576ead4eb73992820bda8b62f").
                        when().
                        get("/weather").
                        then().
                        contentType(JSON).
                        body("name",equalTo(city)).
                        extract().
                        response();

        String temp = response.path("main.temp").toString();
        //    LOGGER.info("["+city+ "][:"+temp+"]");
        synchronized(results){
            results.put(city,temp);
        }

        return  temp;
    }

    WeatherAPI( String name, String cityName) {
        threadName = name;
        this.cityName = cityName;
        LOGGER.info("Creating " +  threadName );
    }
    @Override
    public void run() {
        LOGGER.info("run ");
        try {
            LOGGER.info("Thread: " + threadName);
            getTempFromCity(cityName);
            // Let the thread sleep for a while.
            Thread.sleep(50);

        } catch (InterruptedException e) {
            LOGGER.error("Thread " +  threadName + " interrupted.");
        }
        LOGGER.info("Thread " +  threadName + " exiting.");
    }

    public void start () {
        LOGGER.info("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}
