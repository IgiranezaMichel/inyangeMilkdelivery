package com.inyangedemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inyangedemo.Model.Milk;
import com.inyangedemo.Repo.MilkRepository;

@Service
public class MilkServices {
    @Autowired private MilkRepository milkRepository;
    public Milk saveOrUpdateMilk(Milk Milk){return milkRepository.save(Milk); } 
    public Milk findMilkById(int id){return milkRepository.findById(id).orElse(null); }
    public void deleteMilk(Milk Milk){milkRepository.delete(Milk);}
    public List<Milk>getAllMilk(){return milkRepository.findAll();}
    public List<Milk> findAllMilkByPackageType(String packages){ return milkRepository.findAllByPackageType(packages);}
}
