package com.csci5308.w22.wiseshopping.models;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Elizabeth James
 */
@EqualsAndHashCode
@Entity
@Table
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private int id;

    @Column(name = "location_name")
    private String name;

    private String zipcode;

    private String province;

    private String country;

    public Location(String name, String zipcode, String province, String country) {
        this.name = name;
        this.zipcode = zipcode;
        this.province = province;
        this.country = country;
    }
    public Location(){
    }

    public Location(int id, String name, String zipcode, String province, String country) {
        this.id = id;
        this.name = name;
        this.zipcode = zipcode;
        this.province = province;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
