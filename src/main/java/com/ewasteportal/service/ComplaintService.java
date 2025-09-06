package com.ewasteportal.service;

import com.ewasteportal.model.Complaint;
import com.ewasteportal.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComplaintService {
    
    @Autowired
    private ComplaintRepository complaintRepository;
    
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }
    
    public Optional<Complaint> getComplaintById(Long id) {
        return complaintRepository.findById(id);
    }
    
    public Complaint createComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }
    
    public Complaint updateComplaint(Long id, Complaint complaintDetails) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + id));
        
        complaint.setDetails(complaintDetails.getDetails());
        complaint.setStatus(complaintDetails.getStatus());
        
        return complaintRepository.save(complaint);
    }
    
    public void deleteComplaint(Long id) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + id));
        
        complaintRepository.delete(complaint);
    }
    
    public List<Complaint> getComplaintsByUserId(Long userId) {
        return complaintRepository.findByUserId(userId);
    }
    
    public List<Complaint> getComplaintsByStatus(String status) {
        return complaintRepository.findByStatus(status);
    }
    
    public Complaint updateComplaintStatus(Long id, String status) {
        Complaint complaint = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found with id: " + id));
        
        complaint.setStatus(status);
        return complaintRepository.save(complaint);
    }
    
    public long getResolvedComplaintsCount() {
        return complaintRepository.findByStatus("RESOLVED").size();
    }
}