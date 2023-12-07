package com.inyangedemo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inyangedemo.Model.Location;
import com.inyangedemo.Model.School;
import com.inyangedemo.Model.User;

public interface SchoolRepository extends JpaRepository<School,Integer>{
    List<School> findAllByName(String name);
    List<School> findAllByUser(User user);
    List<School> findAllByLocationId(Location location);
    
}
