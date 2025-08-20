package com.springboot.store;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ProjectService {
   private ProjectRepository repo;

    ProjectService(ProjectRepository repo){
        this.repo = repo;
    }
    public void add(String name){
      repo.save(new Project(name));
    }
    public void delete(Integer id){
        repo.deleteById(id);
    }
    public Project getProjectById(Integer id){
         return  repo.findById(id)
         .orElseThrow( ()->new RuntimeException("No project with ID: " + id + " exists"));
         
    }
    public Boolean existsById(Integer id){
      return repo.existsById(id);
    }
    public List<Project> getAllProjects(){
        return repo.findAll();
    }
    public void update(Integer id,Project updatedProject){
        Optional<Project> existingProjectId = repo.findById(id);
      if(existingProjectId.isPresent()){
        Project existingProject = existingProjectId.get();
        existingProject.setName(updatedProject.getName());
        repo.save(existingProject);
      }else{
        throw new RuntimeException("No project with ID: " + id + " exists");
      }
    }
}
