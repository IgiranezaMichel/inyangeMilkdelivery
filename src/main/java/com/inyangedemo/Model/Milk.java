package com.inyangedemo.Model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Milk {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String packageType;
    private String unit;
    private int quantity;
    private  int price; 
    @OneToMany(mappedBy="milk")
    private List<Distributor>distributorList;
}
