package com.inyangedemo.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    private String password;
    private String role;
    @OneToMany(mappedBy = "user")
    private List<School> schoolList;
    @OneToMany(mappedBy = "user")
    private List<Distributor> distributorList;
     @OneToMany(mappedBy="distributor")
    private List<Ordering>orderList;
    public User(String name,String phoneNumber,String email,String password,String role)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
}
