package com.inyangedemo.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class School {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;
    private int studentNumber;
    @ManyToOne
    private Location locationId;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "school")
    private List<Ordering>orderList;
}
