package com.springboot.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.store.Project;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{
    // This interface will automatically provide CRUD operations for Project entities
}
