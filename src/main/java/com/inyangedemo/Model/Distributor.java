package com.inyangedemo.Model;
import jakarta.persistence.*;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Distributor {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Location location;
    @ManyToOne
    private User user;
    @ManyToOne
    private Milk milk;
    private double quantity;
}
