package com.merav.testing;

public class City {

    private String country;
    private String name;
    private String lat;
    private String lng;

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("country "+country);
        stringBuilder.append('\n');
        stringBuilder.append("name "+name);
        stringBuilder.append('\n');
        stringBuilder.append("lat "+lat);
        stringBuilder.append('\n');
        stringBuilder.append("lng "+lng);

        return stringBuilder.toString();
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }
}
