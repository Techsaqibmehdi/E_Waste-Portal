package com.ewasteportal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recycler_centers")
public class RecyclerCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String location;
    private String city;
    private String contactInfo;

    // Constructors
    public RecyclerCenter() {}

    public RecyclerCenter(String name, String description, String location, String city, String contactInfo) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.city = city;
        this.contactInfo = contactInfo;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
}