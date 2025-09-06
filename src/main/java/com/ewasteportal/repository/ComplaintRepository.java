package com.ewasteportal.repository;

import com.ewasteportal.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    List<Complaint> findByUserId(Long userId);
    List<Complaint> findByStatus(String status);
}