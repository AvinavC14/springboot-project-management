package com.springboot.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.store.SoftwareEngineer;

@Repository
public interface SoftwareEngineerRepository extends JpaRepository<SoftwareEngineer, Integer> {
    // This interface will automatically provide CRUD operations for SoftwareEngineer entities
    
}
