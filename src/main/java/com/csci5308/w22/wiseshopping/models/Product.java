package com.csci5308.w22.wiseshopping.models;

import lombok.*;

import javax.persistence.*;

/**
 * @author Nilesh
*/
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description")
    private String productDescription;

    public Product(String productName, String productDesc) {
        this.productName = productName;
        this.productDescription = productDesc;
    }


    public Product(String productName) {
        this.productName = productName;
    }
}
