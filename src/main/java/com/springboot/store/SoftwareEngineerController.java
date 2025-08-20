package com.springboot.store;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SoftwareEngineers")
public class SoftwareEngineerController {
    private SoftwareEngineerService service;
    private ProjectService projectService;
    
    public SoftwareEngineerController(SoftwareEngineerService service, ProjectService projectService) {
      this.projectService = projectService;
     this.service=service;
    }
    @GetMapping("/getAll")
    public List<SoftwareEngineer> getAll(){
        return service.getAllEngineers();
    }
    @PostMapping("/AddEngineer")
    public void add(@RequestParam String n,@RequestParam String ts,@RequestParam Integer projectId){
        Project project=projectService.getProjectById(projectId);
          service.saveEngineer(new SoftwareEngineer(n,ts,project));
    }
    @DeleteMapping("/DeleteEngineer/{id}")
    public void delete(@PathVariable Integer id){
        if(!service.existsById(id)){
            throw new RuntimeException("No engineer with Id :" + id + " exists");
        }
        service.deleteEngineer(id);
    }
    @PutMapping("/UpdateEngineer/{id}")
    public void update(@PathVariable Integer id,@RequestBody SoftwareEngineer updatedEngineer){
        if(!service.existsById(id)){
            throw new RuntimeException("No engineer with ID: " + id + " exists");
        }
      service.updateEngineer(id, updatedEngineer);
    }
}
