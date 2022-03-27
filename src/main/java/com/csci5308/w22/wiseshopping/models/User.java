package com.csci5308.w22.wiseshopping.models;
import com.csci5308.w22.wiseshopping.utils.Util;
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
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "contact")
    private String contact;
    @Column(name = "register_at" ,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp register_at;
    @Column(name = "security_code")
    private String security_code;


    public User(int userId) {
        this.id = userId;
    }
    public User(String firstName, String lastName, String email, String password, String contact) {
        this.email = email;
        this.password = Util.encode(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
    }

    public User(String firstName, String lastName, String email, String password, String contact, String security_code) {
        this.email = email;
        this.password = Util.encode(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.security_code=security_code;
    }

    public int getUserId() {
        return id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
        this.password = Util.encode(password);
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
    public String getSecurity_code() {
        return security_code;
    }
    public void setSecurity_code(String security_code) {
        this.security_code = Util.encode(security_code);
    }
}