package com.csci5308.w22.wiseshopping.models;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author Pavithra Gunasekaran
 */
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "product")
    private String product;

    @Column(name = "quantity_ordered")
    private int quantityOrdered;

    @Column(name = "price_each")
    private double priceEach;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "purchase_address")
    private String purchaseAddress;



    public Sales(String product) {
        this.product = product;
    }

    public Sales(int orderId, String product, int quantityOrdered, double priceEach, Date orderDate, String purchaseAddress) {
        this.orderId = orderId;
        this.product = product;
        this.quantityOrdered = quantityOrdered;
        this.priceEach = priceEach;
        this.orderDate = orderDate;
        this.purchaseAddress = purchaseAddress;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }

    public double getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(double priceEach) {
        this.priceEach = priceEach;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPurchaseAddress() {
        return purchaseAddress;
    }

    public void setPurchaseAddress(String purchaseAddress) {
        this.purchaseAddress = purchaseAddress;
    }
}
