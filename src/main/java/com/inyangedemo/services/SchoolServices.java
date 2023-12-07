package com.inyangedemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inyangedemo.Model.Location;
import com.inyangedemo.Model.School;
import com.inyangedemo.Model.User;
import com.inyangedemo.Repo.SchoolRepository;

@Service
public class SchoolServices {
    @Autowired private SchoolRepository schoolRepository;
    public School saveOrUpdateSchool(School School){return schoolRepository.save(School); } 
    public School findSchoolById(int id){return schoolRepository.findById(id).orElse(null); }
    public void deleteSchool(School School){schoolRepository.delete(School);}
    public List<School>getAllSchools(){return schoolRepository.findAll();} 
    public List<School>findListOfSchoolByLocation(Location location){return schoolRepository.findAllByLocationId(location);}
    public List<School>findListOfSchoolByName(String name){return schoolRepository.findAllByName(name);}
    public List<School>findListOfSchoolByUser(User user){return schoolRepository.findAllByUser(user);}
    
}
