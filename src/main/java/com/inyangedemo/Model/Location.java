package com.inyangedemo.Model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Location {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String name;
    private String type;
    @JsonIgnore
    @ManyToOne()
    private Location locationId;
    @JsonIgnore
    @OneToMany(targetEntity = Location.class,cascade = CascadeType.ALL,mappedBy = "locationId")
    private List<Location>LocationList;
    @JsonIgnore
    @OneToMany(targetEntity = Location.class,cascade = CascadeType.ALL,mappedBy = "locationId")
    private List<School>schoolList;
    @JsonIgnore
    @OneToMany(targetEntity = Location.class,cascade = CascadeType.ALL,mappedBy = "locationId")
    private List<Distributor>distributorList;
}
