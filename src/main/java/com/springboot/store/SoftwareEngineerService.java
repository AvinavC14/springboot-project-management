package com.springboot.store;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class SoftwareEngineerService {
    private  SoftwareEngineerRepository repo;
    SoftwareEngineerService(SoftwareEngineerRepository repo){
       this.repo=repo;
    }
    public List<SoftwareEngineer> getAllEngineers(){
        return repo.findAll();
    }
    public void saveEngineer(SoftwareEngineer eng){
        repo.save(eng);
    }
    public void deleteEngineer(Integer id){
        repo.deleteById(id);
    }
    public boolean existsById(Integer id){
        return repo.existsById(id);
    }
    public void updateEngineer(Integer id,SoftwareEngineer updatedEngineer){
      Optional<SoftwareEngineer> existingEngineerId =repo.findById(id);

      if(existingEngineerId.isPresent()){
        SoftwareEngineer existingEngineer = existingEngineerId.get();
        existingEngineer.setName(updatedEngineer.getName());
        existingEngineer.setTechStack(updatedEngineer.getTechStack());
      }else{
        throw new RuntimeException("No engineer with ID: "+id+ "exists");
      }
  
    }
  
}
