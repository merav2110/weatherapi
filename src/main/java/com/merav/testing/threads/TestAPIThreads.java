package com.merav.testing.threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TestAPIThreads {

    final private static Logger LOGGER = LoggerFactory.getLogger(TestAPIThreads.class.getName());
    public static void main(String args[]) {

        List<String> cities = new ArrayList<>();
        cities.add("London");
        cities.add("Tel Aviv");
        cities.add("Petah Tikva");
        cities.add("New York");

        WeatherAPI[] threads = new WeatherAPI[cities.size()];

        for(int i =0 ; i<cities.size() ; i++){
            LOGGER.info("City :"+cities.get(i));
            threads[i] = new WeatherAPI(("Thread-"+i),cities.get(i));
            threads[i].start();
        }


    }
}
