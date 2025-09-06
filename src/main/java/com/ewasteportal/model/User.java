package com.ewasteportal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Phone number is required")
    private String phone;

    private String city;
    
    private String role = "USER"; // USER or ADMIN

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EwasteItem> ewasteItems = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Complaint> complaints = new ArrayList<>();

    // Constructors
    public User() {}

    public User(String name, String email, String password, String address, String phone, String city) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.city = city;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public List<EwasteItem> getEwasteItems() { return ewasteItems; }
    public void setEwasteItems(List<EwasteItem> ewasteItems) { this.ewasteItems = ewasteItems; }
    public List<Complaint> getComplaints() { return complaints; }
    public void setComplaints(List<Complaint> complaints) { this.complaints = complaints; }
}