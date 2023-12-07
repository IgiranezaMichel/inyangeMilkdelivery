package com.inyangedemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inyangedemo.Model.Distributor;
import com.inyangedemo.Model.Location;
import com.inyangedemo.Model.Milk;
import com.inyangedemo.Model.User;
import com.inyangedemo.Repo.DistributorRepository;
import java.util.List;
@Service
public class DistributionServices {
@Autowired private DistributorRepository dRepository;
// save distributor information
public Distributor saveOrUpdateDistributor(Distributor distributor)
{ return dRepository.save(distributor);}
// find by id
public Distributor findDistributorById(int id){return dRepository.findById(id).orElse(null);}
// delete distributo
public String deleteDestributor(Distributor distributor)
{ try { dRepository.delete(distributor);  return "Distributor deleted successfully";} catch (Exception e) { return "Distributor not found";}}
// get All distributor
public List<Distributor> getAllDistributor(){return dRepository.findAll();}
// pageable distribution
public Page<Distributor> getAllDistributorPage(int page,int size,String sort)
{ Pageable pages=PageRequest.of(page, size, Sort.by(sort).descending());return dRepository.findAll(pages);}
public List<Distributor>findListOfDistributorByLocation(Location location){  return dRepository.findAllByLocationId(location);}
public List<Distributor>findListOfDistributorByUser(User user){ return dRepository.findAllByUser(user);}
public List<Distributor>findListOfDistributorByMilk(Milk milk){ return dRepository.findAllByMilk(milk);}
}
