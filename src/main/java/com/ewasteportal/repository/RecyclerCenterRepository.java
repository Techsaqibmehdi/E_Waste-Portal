package com.ewasteportal.repository;

import com.ewasteportal.model.RecyclerCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecyclerCenterRepository extends JpaRepository<RecyclerCenter, Long> {
    List<RecyclerCenter> findByCityContainingIgnoreCase(String city);
    List<RecyclerCenter> findByNameContainingIgnoreCase(String name);
}