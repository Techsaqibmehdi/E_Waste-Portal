package com.ewasteportal.repository;

import com.ewasteportal.model.EwasteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EwasteItemRepository extends JpaRepository<EwasteItem, Long> {
    List<EwasteItem> findByUserId(Long userId);
    List<EwasteItem> findByStatus(String status);
    List<EwasteItem> findByItemTypeContainingIgnoreCase(String itemType);
    List<EwasteItem> findByUserCity(String city);
}