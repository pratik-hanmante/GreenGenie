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
    private UUID id;

    // ID of the farmer who owns this farm
    @Column(name = "farmer_id", nullable = false)
    private UUID farmerId;

    // Name of the farm
    @Column(nullable = false, length = 255)
    private String name;

    // Location can be GPS coordinates or just an address
    @Column(nullable = false, length = 500)
    private String location;

    // Size of the farm in acres
    @Column(name = "size_in_acres", nullable = false)
    private Double sizeInAcres;

    // Types of crops grown on this farm
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "farm_crops", joinColumns = @JoinColumn(name = "farm_id"))
    @Column(name = "crop_type")
    private List<String> cropTypes;

    // Current status of the farm
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FarmStatus status = FarmStatus.ACTIVE;

    // When the farm was registered
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime registeredAt;

    // Last update timestamp
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Soil type like Loamy, Clay, etc.
    @Column(length = 100)
    private String soilType;

    // Irrigation method used on the farm
    @Enumerated(EnumType.STRING)
    private IrrigationType irrigationType;

    // Latitude and Longitude for precise location
    private Double latitude;
    private Double longitude;

    private String contactNumber;
    private String email;

    // Certifications like Organic, Fair Trade, etc.
    @Column(length = 100)
    private String certification;

    // Source of water for irrigation
    @Column(length = 100)
    private String waterSource;

    // Optional description about the farm
    @Column(columnDefinition = "TEXT")
    private String description;

    // Enums for farm status
    public enum FarmStatus {
        ACTIVE,
        INACTIVE,
        UNDER_INSPECTION,
        SUSPENDED,
        ARCHIVED
    }

    // Enums for irrigation type
    public enum IrrigationType {
        DRIP,
        SPRINKLER,
        FLOOD,
        MANUAL,
        RAINFED,
        PIVOT
    }

    
    public void addCropType(String cropType) {
        if (cropTypes != null && !cropTypes.contains(cropType)) {
            cropTypes.add(cropType);
        }
    }

    public void removeCropType(String cropType) {
        if (cropTypes != null) {
            cropTypes.remove(cropType);
        }
    }

    public boolean isActive() {
        return status == FarmStatus.ACTIVE;
    }

    public void activate() {
        status = FarmStatus.ACTIVE;
    }

    public void deactivate() {
        status = FarmStatus.INACTIVE;
    }
}
