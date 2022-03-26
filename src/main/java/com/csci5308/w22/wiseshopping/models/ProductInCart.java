package com.csci5308.w22.wiseshopping.models;

import lombok.*;

import javax.persistence.*;

/**
 * @author Pavithra Gunasekaran
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table
public class ProductInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_in_cart_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id", referencedColumnName = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", referencedColumnName = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id", referencedColumnName = "store_id")
    private Store store;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

    public ProductInCart( Cart cart, Product product, Store store, int quantity, int price) {
        this.id = id;
        this.cart = cart;
        this.product = product;
        this.store = store;
        this.quantity = quantity;
        this.price = price;
    }
}
