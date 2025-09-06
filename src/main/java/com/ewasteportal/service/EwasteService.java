package com.ewasteportal.service;

import com.ewasteportal.model.EwasteItem;
import com.ewasteportal.repository.EwasteItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EwasteService {
    
    @Autowired
    private EwasteItemRepository ewasteItemRepository;
    
    public List<EwasteItem> getAllEwasteItems() {
        return ewasteItemRepository.findAll();
    }
    
    public Optional<EwasteItem> getEwasteItemById(Long id) {
        return ewasteItemRepository.findById(id);
    }
    
    public EwasteItem createEwasteItem(EwasteItem ewasteItem) {
        return ewasteItemRepository.save(ewasteItem);
    }
    
    public EwasteItem updateEwasteItem(Long id, EwasteItem ewasteItemDetails) {
        EwasteItem ewasteItem = ewasteItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ewaste item not found with id: " + id));
        
        ewasteItem.setItemType(ewasteItemDetails.getItemType());
        ewasteItem.setDescription(ewasteItemDetails.getDescription());
        ewasteItem.setWeight(ewasteItemDetails.getWeight());
        ewasteItem.setLocation(ewasteItemDetails.getLocation());
        ewasteItem.setStatus(ewasteItemDetails.getStatus());
        
        return ewasteItemRepository.save(ewasteItem);
    }
    
    public void deleteEwasteItem(Long id) {
        EwasteItem ewasteItem = ewasteItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ewaste item not found with id: " + id));
        
        ewasteItemRepository.delete(ewasteItem);
    }
    
    public List<EwasteItem> getEwasteItemsByUserId(Long userId) {
        return ewasteItemRepository.findByUserId(userId);
    }
    
    public List<EwasteItem> getEwasteItemsByStatus(String status) {
        return ewasteItemRepository.findByStatus(status);
    }
    
    public List<EwasteItem> searchEwasteItemsByType(String itemType) {
        return ewasteItemRepository.findByItemTypeContainingIgnoreCase(itemType);
    }
    
    public List<EwasteItem> getEwasteItemsByCity(String city) {
        return ewasteItemRepository.findByUserCity(city);
    }
    
    public EwasteItem updateEwasteItemStatus(Long id, String status) {
        EwasteItem ewasteItem = ewasteItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ewaste item not found with id: " + id));
        
        ewasteItem.setStatus(status);
        return ewasteItemRepository.save(ewasteItem);
    }
    
    public long getTotalEwasteItems() {
        return ewasteItemRepository.count();
    }
    
    public long getPendingPickupCount() {
        return ewasteItemRepository.findByStatus("PENDING").size();
    }
}