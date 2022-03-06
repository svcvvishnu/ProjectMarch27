package com.csci5308.w22.wiseshopping.models;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Time;

/**
 * @author Nilesh
*/
@EqualsAndHashCode
@Entity
@Table
public class ProductInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private int inventoryId;

    //Crosscheck if this is One to Many or Many to One
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id", referencedColumnName = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(name = "price")
    private int price;

    @Column(name = "stock")
    private int stock;

    public ProductInventory(){}

    public ProductInventory(Store store, Product product, int price, int stock) {
        this.store = store;
        this.product = product;
        this.price = price;
        this.stock = stock;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
