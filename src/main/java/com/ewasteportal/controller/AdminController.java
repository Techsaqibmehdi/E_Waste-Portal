package com.ewasteportal.controller;

import com.ewasteportal.service.ComplaintService;
import com.ewasteportal.service.EwasteService;
import com.ewasteportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private EwasteService ewasteService;
    
    @Autowired
    private ComplaintService complaintService;
    
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getAdminDashboard() {
        long totalUsers = userService.getTotalUsers();
        long pendingPickups = ewasteService.getPendingPickupCount();
        long resolvedComplaints = complaintService.getResolvedComplaintsCount();
        
        Map<String, Object> response = new HashMap<>();
        response.put("totalUsers", totalUsers);
        response.put("pendingPickups", pendingPickups);
        response.put("resolvedComplaints", resolvedComplaints);
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        
        // Simple admin authentication
        if ("admin".equals(username) && "admin123".equals(password)) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Admin login successful");
            response.put("role", "ADMIN");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invalid admin credentials");
            return ResponseEntity.status(401).body(response);
        }
    }
}