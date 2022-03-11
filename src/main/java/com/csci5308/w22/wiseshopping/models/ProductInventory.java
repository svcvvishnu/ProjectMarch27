package com.csci5308.w22.wiseshopping.models;

import lombok.*;

import javax.persistence.*;

/**
 * @author Nilesh
*/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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


    public ProductInventory(Store store, Product product, int price, int stock) {
        this.store = store;
        this.product = product;
        this.price = price;
        this.stock = stock;
    }

}
