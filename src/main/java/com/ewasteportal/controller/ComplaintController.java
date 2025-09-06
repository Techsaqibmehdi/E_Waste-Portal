package com.ewasteportal.controller;

import com.ewasteportal.model.Complaint;
import com.ewasteportal.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "http://localhost:3000")
public class ComplaintController {
    
    @Autowired
    private ComplaintService complaintService;
    
    @GetMapping
    public List<Complaint> getAllComplaints() {
        return complaintService.getAllComplaints();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable Long id) {
        Optional<Complaint> complaint = complaintService.getComplaintById(id);
        return complaint.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Complaint createComplaint(@Valid @RequestBody Complaint complaint) {
        return complaintService.createComplaint(complaint);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Complaint> updateComplaint(@PathVariable Long id, @Valid @RequestBody Complaint complaintDetails) {
        try {
            Complaint updatedComplaint = complaintService.updateComplaint(id, complaintDetails);
            return ResponseEntity.ok(updatedComplaint);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComplaint(@PathVariable Long id) {
        try {
            complaintService.deleteComplaint(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/user/{userId}")
    public List<Complaint> getComplaintsByUserId(@PathVariable Long userId) {
        return complaintService.getComplaintsByUserId(userId);
    }
    
    @GetMapping("/status/{status}")
    public List<Complaint> getComplaintsByStatus(@PathVariable String status) {
        return complaintService.getComplaintsByStatus(status);
    }
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<Complaint> updateComplaintStatus(@PathVariable Long id, @RequestBody Map<String, String> statusMap) {
        try {
            String status = statusMap.get("status");
            Complaint updatedComplaint = complaintService.updateComplaintStatus(id, status);
            return ResponseEntity.ok(updatedComplaint);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/stats/resolved")
    public ResponseEntity<Map<String, Long>> getResolvedComplaintsCount() {
        long resolvedCount = complaintService.getResolvedComplaintsCount();
        
        Map<String, Long> response = new HashMap<>();
        response.put("resolvedComplaints", resolvedCount);
        
        return ResponseEntity.ok(response);
    }
}