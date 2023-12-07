package com.inyangedemo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inyangedemo.Model.Milk;

public interface MilkRepository extends JpaRepository<Milk,Integer>{
    List<Milk> findAllByPackageType(String packages);  
}
