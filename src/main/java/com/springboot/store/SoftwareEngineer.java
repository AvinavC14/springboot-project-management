package com.springboot.store;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "software_engineers")
public class SoftwareEngineer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String techStack;
    
    @ManyToOne
    @JoinColumn(name="projectId", nullable = false)
    @JsonIgnore
    public Project project;

    public SoftwareEngineer() {}

    // Constructor
    public SoftwareEngineer(String name, String techStack, Project project) {
        
        this.name = name;
        this.techStack = techStack;
        this.project = project;

    }
  

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getTechStack() { return techStack; }
    public Integer getProjectId() { return project==null ? null:project.getprojectId(); }

    public void setName(String name) { this.name = name; }
    public void setTechStack(String techStack) { this.techStack = techStack; }
    public void setProject(Project project) { this.project = project; }
    

    
}
