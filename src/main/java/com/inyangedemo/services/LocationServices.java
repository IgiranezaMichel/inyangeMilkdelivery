package com.inyangedemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.inyangedemo.Model.Location;
import com.inyangedemo.Repo.LocationRepository;

@Service
public class LocationServices {
   @Autowired private LocationRepository locationRepository;
   // save location
   public Location saveOrUpdateLocation(Location location){return locationRepository.save(location); } 
   public Location findLocationById(int id){return locationRepository.findById(id).orElse(null); }
   public void deleteLocation(Location location){locationRepository.delete(location);}
   public List<Location>getAllLocation(){return locationRepository.findAll();}
   public List<Location> findListOfLocationByType(String type){return locationRepository.findAllByType(type);}
   public List<Location> findListOfLocationBylocationId(Location location){
      return locationRepository.findAllBylocationId(location);}
}
