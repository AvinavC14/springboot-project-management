package com.springboot.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{
    // This interface will automatically provide CRUD operations for Project entities
}
