package com.springboot.store;

import java.util.List;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Project")
public class ProjectController {
    private ProjectService service;
    ProjectController(ProjectService service){
       this.service=service;
    }
    @PostMapping("/add")
    public void add(@RequestParam String name){
     service.add(name);
    }
    @GetMapping("/getAll")
    public List<Project> getAll(){
        return service.getAllProjects();
    }
    @DeleteMapping("/delete/{Id}")
    public void delete(@PathVariable Integer Id){
        if(service.existsById(Id)){
       service.delete(Id);
        }else{throw new RuntimeException("No project with Id :"+Id+"exists");}
       }
   @PutMapping("/update/{id}")
   public void update(@PathVariable Integer id,@RequestParam String n){
        if(!service.existsById(id)){
                throw new RuntimeException("No project with ID: " + id + " exists");
            }
    service.update(id, new Project(n));
   }
}
