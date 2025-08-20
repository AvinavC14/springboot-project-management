package com.springboot.store;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "projects")
@JsonPropertyOrder({"projectId", "name", "engineers"})
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;
    private String name;
   
    @OneToMany(mappedBy = "project")
    public List<SoftwareEngineer> engineers = new ArrayList<>();

    //constructors
    public Project() {
    };

    public Project(String name) {
        this.name = name;
    }

    // setter and getter methods
    public Integer getprojectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }
   public List<SoftwareEngineer> getEngineers(){
    return engineers;
   }

    public void setName(String n) {
        name = n;
    }
   public void setEngineers(List<SoftwareEngineer> engineers){
    this.engineers=engineers;
   }
}
