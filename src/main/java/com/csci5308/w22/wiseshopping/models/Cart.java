package com.csci5308.w22.wiseshopping.models;

import com.csci5308.w22.wiseshopping.utils.Constants;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User user;


    @Column(name = "status")
    private String status;

    public Cart(User user,String status) {
        this.user = user;
        this.status=status;
    }

    public Cart(User user) {
        this.user = user;

    }

    public Cart(int id){
        this.id=id;
    }
}