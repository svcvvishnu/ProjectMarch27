package com.csci5308.w22.wiseshopping.models;

import lombok.EqualsAndHashCode;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;

/**
 * @author Elizabeth James
 */
@EqualsAndHashCode
@Entity
@Table(name = "merchant_details")
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "merchant_id")
    private int merchantId;

    @Column(name =  "merchant_name")
    private String merchantName;

    @Column(name =   "password")
    private String password;

    @Column(name =  "email")
    private String email;

    public Merchant(String merchantName, String email , String password) {
        this.merchantName = merchantName;
        this.password = encode(password);
        this.email = email;
    }

    public Merchant(String email , String password) {
        this.password = encode(password);
        this.email = email;
    }


    public Merchant(){

    }

    public Merchant(int merchantId, String merchantName, String password, String email) {
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.password = password;
        this.email = email;
    }

    /**
     * this encodes the password using sha 256 algorithm
     * @param password password
     * @return encoded password
     */
    private String encode(String password) {
        return DigestUtils.sha256Hex(password);
    }


    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = encode(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
