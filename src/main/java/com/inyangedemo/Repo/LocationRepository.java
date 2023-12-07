package com.inyangedemo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inyangedemo.Model.Location;

public interface LocationRepository  extends JpaRepository<Location,Integer>{

    List<Location> findAllByType(String type);
    List<Location> findAllBylocationId(Location id);
}
