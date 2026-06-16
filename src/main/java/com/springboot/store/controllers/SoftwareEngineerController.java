package com.springboot.store.controllers;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.store.dto.CreateEngineerRequest;
import com.springboot.store.entity.Project;
import com.springboot.store.entity.SoftwareEngineer;
import com.springboot.store.services.ProjectService;
import com.springboot.store.services.SoftwareEngineerService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public void add(@Valid @RequestBody CreateEngineerRequest request) {
    Project project = projectService.getProjectById(request.projectId());
    service.saveEngineer(new SoftwareEngineer(request.name(), request.techStack(), project));
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
    @GetMapping("/getAllPaged")
public Page<SoftwareEngineer> getAllPaged(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "20") int size
) {
    return service.getAllEngineersPaged(PageRequest.of(page, size));
}
}
