package com.merav.testing;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import static com.merav.testing.sequential.WeatherAPI.getTempFromCity;

public class ReadJsonFile {

    //reading json using google-gson
    final private static Logger LOGGER = LoggerFactory.getLogger(ReadJsonFile.class.getName());
    static private City[] cities;

    public static void main(String[] args) throws IOException {
        try(Reader reader = new InputStreamReader(ReadJsonFile.class.getResourceAsStream("/Cities.json"), "UTF-8")){
            Gson gson = new GsonBuilder().create();
            cities = gson.fromJson(reader, City[].class);
            for(City city :cities){
                LOGGER.info(city.toString());
            }
        }

        List<City> citiesList = Arrays.asList(cities);
        for(City city : citiesList){
            getTempFromCity(city.getName());
        }
    }
}
