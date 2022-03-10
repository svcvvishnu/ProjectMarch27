package com.csci5308.w22.wiseshopping.models;

import com.csci5308.w22.wiseshopping.utils.Constants;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User user;

    private String status = Constants.NOTSHARED;

    public Cart(User user) {
        this.user = user;
    }
}