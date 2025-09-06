package com.ewasteportal.controller;

import com.ewasteportal.model.EwasteItem;
import com.ewasteportal.service.EwasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/ewaste")
@CrossOrigin(origins = "http://localhost:3000")
public class EwasteController {
    
    @Autowired
    private EwasteService ewasteService;
    
    @GetMapping
    public List<EwasteItem> getAllEwasteItems() {
        return ewasteService.getAllEwasteItems();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EwasteItem> getEwasteItemById(@PathVariable Long id) {
        Optional<EwasteItem> ewasteItem = ewasteService.getEwasteItemById(id);
        return ewasteItem.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public EwasteItem createEwasteItem(@Valid @RequestBody EwasteItem ewasteItem) {
        return ewasteService.createEwasteItem(ewasteItem);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EwasteItem> updateEwasteItem(@PathVariable Long id, @Valid @RequestBody EwasteItem ewasteItemDetails) {
        try {
            EwasteItem updatedEwasteItem = ewasteService.updateEwasteItem(id, ewasteItemDetails);
            return ResponseEntity.ok(updatedEwasteItem);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEwasteItem(@PathVariable Long id) {
        try {
            ewasteService.deleteEwasteItem(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/user/{userId}")
    public List<EwasteItem> getEwasteItemsByUserId(@PathVariable Long userId) {
        return ewasteService.getEwasteItemsByUserId(userId);
    }
    
    @GetMapping("/status/{status}")
    public List<EwasteItem> getEwasteItemsByStatus(@PathVariable String status) {
        return ewasteService.getEwasteItemsByStatus(status);
    }
    
    @GetMapping("/search")
    public List<EwasteItem> searchEwasteItemsByType(@RequestParam String type) {
        return ewasteService.searchEwasteItemsByType(type);
    }
    
    @GetMapping("/city/{city}")
    public List<EwasteItem> getEwasteItemsByCity(@PathVariable String city) {
        return ewasteService.getEwasteItemsByCity(city);
    }
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<EwasteItem> updateEwasteItemStatus(@PathVariable Long id, @RequestBody Map<String, String> statusMap) {
        try {
            String status = statusMap.get("status");
            EwasteItem updatedEwasteItem = ewasteService.updateEwasteItemStatus(id, status);
            return ResponseEntity.ok(updatedEwasteItem);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/stats/count")
    public ResponseEntity<Map<String, Long>> getEwasteStats() {
        long totalItems = ewasteService.getTotalEwasteItems();
        long pendingPickups = ewasteService.getPendingPickupCount();
        
        Map<String, Long> response = new HashMap<>();
        response.put("totalItems", totalItems);
        response.put("pendingPickups", pendingPickups);
        
        return ResponseEntity.ok(response);
    }
}