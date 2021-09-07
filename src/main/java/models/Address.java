package models;

import lombok.Data;

import java.util.List;

@Data
public class Address {
    String street;
    String suite;
    String city;
    String zipcode;
    List<Geo> geo;

}
