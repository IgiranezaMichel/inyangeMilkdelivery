package com.inyangedemo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inyangedemo.Model.Distributor;
import com.inyangedemo.Model.Location;
import com.inyangedemo.Model.Milk;
import com.inyangedemo.Model.User;

public interface DistributorRepository extends JpaRepository<Distributor,Integer>{

    List<Distributor> findAllByLocationId(Location location);

    List<Distributor> findAllByUser(User user);

    List<Distributor> findAllByMilk(Milk milk);
    
}
