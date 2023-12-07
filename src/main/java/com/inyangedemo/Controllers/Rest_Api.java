package com.inyangedemo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.inyangedemo.Model.Location;
import com.inyangedemo.services.LocationServices;


@RequestMapping(value = "/api")
@RestController
public class Rest_Api {
    @Autowired private LocationServices locationServices;
    @GetMapping(value = "/location/{id}")
    public List<Location> getLocationId(@PathVariable int id)
    {   Location location = locationServices.findLocationById(id);
        return locationServices.findListOfLocationBylocationId(location);
    }
}
