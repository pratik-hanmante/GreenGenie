package com.pm.farmservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "farms", indexes = {
        @Index(name = "idx_farmer_id", columnList = "farmer_id"),
        @Index(name = "idx_status", columnList = "status"),
        @Index(name = "idx_location", columnList = "location")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "farmer_id", nullable = false)
    private UUID farmerId; // References User in auth-service

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "location", nullable = false, length = 500)
    private String location; // GPS coordinates or address (e.g., "18.5204,73.8567" or "Pune, Maharashtra")

    @Column(name = "size_in_acres", nullable = false)
    private Double sizeInAcres;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "farm_crops",
            joinColumns = @JoinColumn(name = "farm_id")
    )
    @Column(name = "crop_type")
    private List<String> cropTypes; // ["wheat", "rice", "corn", "tomato"]

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private FarmStatus status = FarmStatus.ACTIVE;

    @CreationTimestamp
    @Column(name = "registered_at", nullable = false, updatable = false)
    private LocalDateTime registeredAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "soil_type", length = 100)
    private String soilType; // e.g., "Loamy", "Clay", "Sandy", "Silt"

    @Enumerated(EnumType.STRING)
    @Column(name = "irrigation_type")
    private IrrigationType irrigationType;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "contact_number", length = 15)
    private String contactNumber;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "certification", length = 100)
    private String certification; // e.g., "Organic", "Fair Trade", "None"

    @Column(name = "water_source", length = 100)
    private String waterSource; // e.g., "Well", "River", "Canal", "Rainwater"

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // Enums
    public enum FarmStatus {
        ACTIVE,
        INACTIVE,
        UNDER_INSPECTION,
        SUSPENDED,
        ARCHIVED
    }

    public enum IrrigationType {
        DRIP,
        SPRINKLER,
        FLOOD,
        MANUAL,
        RAINFED,
        PIVOT
    }

    // Helper methods
    public void addCropType(String cropType) {
        if (!this.cropTypes.contains(cropType)) {
            this.cropTypes.add(cropType);
        }
    }

    public void removeCropType(String cropType) {
        this.cropTypes.remove(cropType);
    }

    public boolean isActive() {
        return this.status == FarmStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = FarmStatus.INACTIVE;
    }

    public void activate() {
        this.status = FarmStatus.ACTIVE;
    }
}
