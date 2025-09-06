package com.ewasteportal.controller;

import com.ewasteportal.model.RecyclerCenter;
import com.ewasteportal.service.RecyclerCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recycler-centers")
@CrossOrigin(origins = "http://localhost:3000")
public class RecyclerCenterController {
    
    @Autowired
    private RecyclerCenterService recyclerCenterService;
    
    @GetMapping
    public List<RecyclerCenter> getAllRecyclerCenters() {
        return recyclerCenterService.getAllRecyclerCenters();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RecyclerCenter> getRecyclerCenterById(@PathVariable Long id) {
        Optional<RecyclerCenter> recyclerCenter = recyclerCenterService.getRecyclerCenterById(id);
        return recyclerCenter.map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public RecyclerCenter createRecyclerCenter(@Valid @RequestBody RecyclerCenter recyclerCenter) {
        return recyclerCenterService.createRecyclerCenter(recyclerCenter);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RecyclerCenter> updateRecyclerCenter(@PathVariable Long id, @Valid @RequestBody RecyclerCenter recyclerCenterDetails) {
        try {
            RecyclerCenter updatedRecyclerCenter = recyclerCenterService.updateRecyclerCenter(id, recyclerCenterDetails);
            return ResponseEntity.ok(updatedRecyclerCenter);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecyclerCenter(@PathVariable Long id) {
        try {
            recyclerCenterService.deleteRecyclerCenter(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/search/city")
    public List<RecyclerCenter> searchRecyclerCentersByCity(@RequestParam String city) {
        return recyclerCenterService.searchRecyclerCentersByCity(city);
    }
    
    @GetMapping("/search/name")
    public List<RecyclerCenter> searchRecyclerCentersByName(@RequestParam String name) {
        return recyclerCenterService.searchRecyclerCentersByName(name);
    }
}