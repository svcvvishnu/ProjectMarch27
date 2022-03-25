package com.csci5308.w22.wiseshopping.models;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;

/**
 * @author Nilesh
*/
@NoArgsConstructor
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Product(String productName, String productDesc) {
        this.productName = productName;
        this.productDescription = productDesc;
    }


    public Product(String productName) {
        this.productName = productName;
    }

    public Product(int productId) {
        this.productId=productId;
    }

    public Product(int productId, String productName, String productDescription) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
    }
}
