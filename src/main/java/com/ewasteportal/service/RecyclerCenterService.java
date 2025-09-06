package com.ewasteportal.service;

import com.ewasteportal.model.RecyclerCenter;
import com.ewasteportal.repository.RecyclerCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecyclerCenterService {
    
    @Autowired
    private RecyclerCenterRepository recyclerCenterRepository;
    
    public List<RecyclerCenter> getAllRecyclerCenters() {
        return recyclerCenterRepository.findAll();
    }
    
    public Optional<RecyclerCenter> getRecyclerCenterById(Long id) {
        return recyclerCenterRepository.findById(id);
    }
    
    public RecyclerCenter createRecyclerCenter(RecyclerCenter recyclerCenter) {
        return recyclerCenterRepository.save(recyclerCenter);
    }
    
    public RecyclerCenter updateRecyclerCenter(Long id, RecyclerCenter recyclerCenterDetails) {
        RecyclerCenter recyclerCenter = recyclerCenterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recycler center not found with id: " + id));
        
        recyclerCenter.setName(recyclerCenterDetails.getName());
        recyclerCenter.setDescription(recyclerCenterDetails.getDescription());
        recyclerCenter.setLocation(recyclerCenterDetails.getLocation());
        recyclerCenter.setCity(recyclerCenterDetails.getCity());
        recyclerCenter.setContactInfo(recyclerCenterDetails.getContactInfo());
        
        return recyclerCenterRepository.save(recyclerCenter);
    }
    
    public void deleteRecyclerCenter(Long id) {
        RecyclerCenter recyclerCenter = recyclerCenterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recycler center not found with id: " + id));
        
        recyclerCenterRepository.delete(recyclerCenter);
    }
    
    public List<RecyclerCenter> searchRecyclerCentersByCity(String city) {
        return recyclerCenterRepository.findByCityContainingIgnoreCase(city);
    }
    
    public List<RecyclerCenter> searchRecyclerCentersByName(String name) {
        return recyclerCenterRepository.findByNameContainingIgnoreCase(name);
    }
}