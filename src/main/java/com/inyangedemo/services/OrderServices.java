package com.inyangedemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.inyangedemo.Model.Ordering;
import com.inyangedemo.Model.School;
import com.inyangedemo.Repo.OrderingRepository;

@Service
public class OrderServices {
    @Autowired private OrderingRepository orderingRepository;
    public Ordering saveOrUpdateOrder(Ordering Ordering){return orderingRepository.save(Ordering); } 
    public Ordering findOrderById(int id){return orderingRepository.findById(id).orElse(null); }
    public void deleteOrder(Ordering Ordering){orderingRepository.delete(Ordering);}
    public List<Ordering>getAllOrders(){return orderingRepository.findAll();}
    public List<Ordering>getAllOrdersBySchool(School school){return orderingRepository.findAllBySchool(school);}
    public List<Ordering>getAllOrderingProfile(boolean isDone)
    {
        return orderingRepository.findAllByisDone(isDone);
    }
}
