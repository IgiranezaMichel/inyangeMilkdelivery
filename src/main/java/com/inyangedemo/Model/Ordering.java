package com.inyangedemo.Model;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ordering {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @ManyToOne
    private School school;
    @ManyToOne
    private Milk milk;
    private int quantity;
    private Date deadLineDate;
    private boolean approvedByAdmin;
    private boolean approvedBySchool;
    @ManyToOne
    private User distributor;
    private boolean isDone;
    private String message;
    private Date createDate=Date.valueOf(LocalDate.now());
    public String getCreateDate()
    {  SimpleDateFormat sdf = new SimpleDateFormat("EEE-MMM-yyyy HH:mm");
    Date date = Date.valueOf(LocalDate.now());
       if(createDate.compareTo(date)==0)
        return "Today";
        return sdf.format(createDate);
    }
}
