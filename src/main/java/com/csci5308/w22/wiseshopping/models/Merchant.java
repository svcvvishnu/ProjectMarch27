package com.csci5308.w22.wiseshopping.models;

import com.csci5308.w22.wiseshopping.utils.Util;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;

/**
 * @author Elizabeth James
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "merchant_details")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "merchant_id")
    private int id;

    @Column(name =  "merchant_name")
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name =   "password")
    private String password;

    @Column(name =  "email")
    private String email;

    public Merchant(String merchantName, String email , String password) {
        this.name = merchantName;
        this.password = Util.encode(password);
        this.email = email;
    }

    public Merchant(String email , String password) {
        this.password = Util.encode(password);
        this.email = email;
    }


    public Merchant(){

    }

    public Merchant(int merchantId, String merchantName, String password, String email) {
        this.id = merchantId;
        this.name = merchantName;
        this.password = password;
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Util.encode(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //TODO:  use rest advice for error codes
}
