package com.springboot.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareEngineerRepository extends JpaRepository<SoftwareEngineer, Integer> {
    // This interface will automatically provide CRUD operations for SoftwareEngineer entities
    
}
