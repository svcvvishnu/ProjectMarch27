package com.csci5308.w22.wiseshopping.models;

import com.csci5308.w22.wiseshopping.utils.Util;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.security.Timestamp;

/**
 * @author Adarsh Kannan
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

    @Column(name="contact")
    private String contact;

    @Column(name = "register_at" ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp register_at;
    @Column(name = "security_code")
    private String security_code;

    public Merchant(int merchantId) {
        this.id = merchantId;
    }

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

    public Merchant(int merchantId, String merchantName, String password, String email,String contact,String security_code) {
        this.id = merchantId;
        this.name = merchantName;
        this.password = Util.encode(password);
        this.email = email;
        this.contact= contact;
        this.security_code=security_code;
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

    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public Timestamp getRegister_at() {
        return register_at;
    }
    public void setRegister_at(Timestamp register_at) {
        this.register_at = register_at;
    }
    public String getSecurity_code() {
        return security_code;
    }
    public void setSecurity_code(String security_code) {
        this.security_code = Util.encode(security_code);
    }


    //TODO:  use rest advice for error codes
}
