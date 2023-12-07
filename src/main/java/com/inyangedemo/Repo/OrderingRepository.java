package com.inyangedemo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inyangedemo.Model.Ordering;
import com.inyangedemo.Model.School;

public interface OrderingRepository extends JpaRepository<Ordering,Integer>{

    List<Ordering> findAllBySchool(School school);

    List<Ordering> findAllByisDone(boolean isDone);
    
}
