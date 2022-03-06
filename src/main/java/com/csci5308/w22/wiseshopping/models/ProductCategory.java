package com.csci5308.w22.wiseshopping.models;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Nilesh
*/
@EqualsAndHashCode
@Entity
@Table
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_category_id")
    private int productCategoryId;

    @ManyToOne
    @JoinColumn(name="product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(name = "product_category_name")
    private String categoryName;

    @Column(name = "product_category_description")
    private String categoryDesc;

    public ProductCategory(){}

    public ProductCategory(Product product, String name, String desc) {
        this.product = product;
        this.categoryName = name;
        this.categoryDesc = desc;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }
}
