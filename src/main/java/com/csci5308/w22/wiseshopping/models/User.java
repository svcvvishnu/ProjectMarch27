package com.csci5308.w22.wiseshopping.models;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.codec.digest.DigestUtils;
import javax.persistence.*;
import java.sql.Timestamp;
/**
 * @author Pavithra Gunasekaran
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "user_details")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name = "first_name")
    private String userFirstName;
    @Column(name = "last_name")
    private String userLastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "contact")
    private String contact;
    @Column(name = "register_at" ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp register_at;
    public User(int userId) {
        this.userId = userId;
    }
    public User(String firstName, String lastName, String email, String password, String contact) {
        this.email = email;
        this.password = encode(password);
        this.userFirstName = firstName;
        this.userLastName = lastName;
        this.contact = contact;
    }
    /**
     * this encodes the password using sha 256 algorithm
     * @param password password
     * @return encoded password
     */
    private String encode(String password) {
        return DigestUtils.sha256Hex(password);
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserFirstName() {
        return userFirstName;
    }
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }
    public String getUserLastName() {
        return userLastName;
    }
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = encode(password);
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public Timestamp getRegister_at() {
        return register_at;
    }
    public void setRegister_at(Timestamp register_at) {
        this.register_at = register_at;
    }
}