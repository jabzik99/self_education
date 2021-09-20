package models;

import lombok.Data;

import java.util.List;

@Data
public class User {
    Integer id;
    String name;
    String username;
    String email;
    List<Address> address;
    String phone;
    String website;
    List<Company> company;
}
