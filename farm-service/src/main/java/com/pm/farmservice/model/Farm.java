package com.pm.farmservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Entity representing a Farm in the agricultural management system.
 * Contains comprehensive information about farm location, size, crops, and operational details.
 */
@Entity
@Table(name = "farms", indexes = {
        @Index(name = "idx_farmer_id", columnList = "farmer_id"),
        @Index(name = "idx_status", columnList = "status"),
        @Index(name = "idx_location", columnList = "latitude,longitude"),
        @Index(name = "idx_registered_at", columnList = "registered_at"),
        @Index(name = "idx_deleted", columnList = "deleted")
})
@SQLDelete(sql = "UPDATE farms SET deleted = true, updated_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted = false")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"cropTypes"})
@EqualsAndHashCode(of = {"id"})
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotNull(message = "Farmer ID is required")
    @Column(name = "farmer_id", nullable = false)
    private UUID farmerId; // References User in auth-service

    @NotBlank(message = "Farm name is required")
    @Size(min = 2, max = 255, message = "Farm name must be between 2 and 255 characters")
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @NotBlank(message = "Location is required")
    @Size(max = 500, message = "Location must not exceed 500 characters")
    @Column(name = "location", nullable = false, length = 500)
    private String location; // Address (e.g., "Pune, Maharashtra, India")

    @NotNull(message = "Farm size is required")
    @DecimalMin(value = "0.01", message = "Farm size must be at least 0.01 acres")
    @DecimalMax(value = "100000.00", message = "Farm size must not exceed 100,000 acres")
    @Column(name = "size_in_acres", nullable = false, precision = 10, scale = 2)
    private BigDecimal sizeInAcres;

    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "farm_crops",
            joinColumns = @JoinColumn(name = "farm_id"),
            indexes = @Index(name = "idx_farm_crops_farm_id", columnList = "farm_id")
    )
    @Column(name = "crop_type", length = 100)
    private List<String> cropTypes = new ArrayList<>();

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    @Builder.Default
    private FarmStatus status = FarmStatus.ACTIVE;

    @CreationTimestamp
    @Column(name = "registered_at", nullable = false, updatable = false)
    private LocalDateTime registeredAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Size(max = 100, message = "Soil type must not exceed 100 characters")
    @Column(name = "soil_type", length = 100)
    private String soilType; // e.g., "Loamy", "Clay", "Sandy", "Silt", "Peaty", "Chalky"

    @Enumerated(EnumType.STRING)
    @Column(name = "irrigation_type", length = 20)
    private IrrigationType irrigationType;

    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    @Column(name = "latitude", precision = 10, scale = 7)
    private Double latitude;

    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    @Column(name = "longitude", precision = 10, scale = 7)
    private Double longitude;

    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Invalid contact number format")
    @Column(name = "contact_number", length = 15)
    private String contactNumber;

    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    @Column(name = "email", length = 255)
    private String email;

    @Size(max = 100, message = "Certification must not exceed 100 characters")
    @Column(name = "certification", length = 100)
    private String certification; // e.g., "Organic", "Fair Trade", "GAP", "GlobalGAP", "None"

    @Size(max = 100, message = "Water source must not exceed 100 characters")
    @Column(name = "water_source", length = 100)
    private String waterSource; // e.g., "Well", "Borewell", "River", "Canal", "Rainwater", "Reservoir"

    @Size(max = 2000, message = "Description must not exceed 2000 characters")
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "average_rainfall", precision = 8, scale = 2)
    private BigDecimal averageRainfall; // in mm per year

    @Column(name = "elevation", precision = 8, scale = 2)
    private BigDecimal elevation; // in meters above sea level

    @Builder.Default
    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    // Enums
    public enum FarmStatus {
        ACTIVE("Active - Operational"),
        INACTIVE("Inactive - Not in use"),
        UNDER_INSPECTION("Under Inspection"),
        SUSPENDED("Suspended - Compliance issue"),
        ARCHIVED("Archived - Historical record");

        private final String description;

        FarmStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum IrrigationType {
        DRIP("Drip Irrigation"),
        SPRINKLER("Sprinkler System"),
        FLOOD("Flood Irrigation"),
        MANUAL("Manual Watering"),
        RAINFED("Rain-fed"),
        PIVOT("Center Pivot"),
        SUBSURFACE("Subsurface Drip"),
        MICRO_SPRINKLER("Micro Sprinkler");

        private final String description;

        IrrigationType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    // Business Logic Helper Methods

    public boolean addCropType(String cropType) {
        if (cropType == null || cropType.trim().isEmpty()) {
            throw new IllegalArgumentException("Crop type cannot be null or empty");
        }

        if (this.cropTypes == null) {
            this.cropTypes = new ArrayList<>();
        }

        String normalizedCrop = cropType.trim().toLowerCase();
        if (!this.cropTypes.contains(normalizedCrop)) {
            this.cropTypes.add(normalizedCrop);
            return true;
        }
        return false;
    }

    /**
     * Removes a crop type from the farm.
     * @param cropType the crop type to remove
     * @return true if removed, false if not found
     */
    public boolean removeCropType(String cropType) {
        if (this.cropTypes == null || cropType == null) {
            return false;
        }
        return this.cropTypes.remove(cropType.trim().toLowerCase());
    }

    /**
     * Checks if the farm is currently active.
     * @return true if status is ACTIVE
     */
    public boolean isActive() {
        return this.status == FarmStatus.ACTIVE;
    }

    /**
     * Checks if the farm is operational (active or under inspection).
     * @return true if operational
     */
    public boolean isOperational() {
        return this.status == FarmStatus.ACTIVE ||
                this.status == FarmStatus.UNDER_INSPECTION;
    }

    /**
     * Deactivates the farm by setting status to INACTIVE.
     */
    public void deactivate() {
        if (this.status == FarmStatus.ARCHIVED) {
            throw new IllegalStateException("Cannot deactivate an archived farm");
        }
        this.status = FarmStatus.INACTIVE;
    }

    /**
     * Activates the farm by setting status to ACTIVE.
     */
    public void activate() {
        if (this.status == FarmStatus.ARCHIVED) {
            throw new IllegalStateException("Cannot activate an archived farm");
        }
        if (this.status == FarmStatus.SUSPENDED) {
            throw new IllegalStateException("Cannot activate a suspended farm. Remove suspension first.");
        }
        this.status = FarmStatus.ACTIVE;
    }

    /**
     * Suspends the farm due to compliance or other issues.
     */
    public void suspend() {
        if (this.status == FarmStatus.ARCHIVED) {
            throw new IllegalStateException("Cannot suspend an archived farm");
        }
        this.status = FarmStatus.SUSPENDED;
    }

    /**
     * Archives the farm (soft delete).
     */
    public void archive() {
        this.status = FarmStatus.ARCHIVED;
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
    }

    /**
     * Checks if GPS coordinates are available.
     * @return true if both latitude and longitude are set
     */
    public boolean hasGpsCoordinates() {
        return this.latitude != null && this.longitude != null;
    }

    /**
     * Gets the total number of crop types.
     * @return count of crop types
     */
    public int getCropTypeCount() {
        return this.cropTypes != null ? this.cropTypes.size() : 0;
    }

    /**
     * Checks if the farm grows a specific crop.
     * @param cropType the crop type to check
     * @return true if the crop is grown
     */
    public boolean growsCrop(String cropType) {
        if (this.cropTypes == null || cropType == null) {
            return false;
        }
        return this.cropTypes.contains(cropType.trim().toLowerCase());
    }

    /**
     * Updates GPS coordinates.
     * @param latitude the latitude coordinate
     * @param longitude the longitude coordinate
     */
    public void updateGpsCoordinates(Double latitude, Double longitude) {
        if (latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        }
        if (longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        }
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Checks if the farm is certified organic.
     * @return true if organic certification exists
     */
    public boolean isOrganicCertified() {
        return this.certification != null &&
                this.certification.toLowerCase().contains("organic");
    }

    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = FarmStatus.ACTIVE;
        }
        if (this.deleted == null) {
            this.deleted = false;
        }
        if (this.cropTypes == null) {
            this.cropTypes = new ArrayList<>();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        // Additional validation or business logic before update
        if (this.deleted && this.deletedAt == null) {
            this.deletedAt = LocalDateTime.now();
        }
    }
}