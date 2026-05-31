package com.cloud.imc;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "imc_records")
public class ImcRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double weight;
    private double height;
    private double imcValue;
    private String category;
    private LocalDateTime createdAt;

    // Constructeur par défaut pour JPA
    public ImcRecord() {
    }

    // Constructeur complet
    public ImcRecord(String name, double weight, double height, double imcValue, String category) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.imcValue = imcValue;
        this.category = category;
        this.createdAt = LocalDateTime.now();
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public double getImcValue() { return imcValue; }
    public void setImcValue(double imcValue) { this.imcValue = imcValue; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}