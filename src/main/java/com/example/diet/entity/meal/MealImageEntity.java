package com.example.diet.entity.meal;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @formatter:off
@Entity
@Table(schema = "diet", name = "t_meal_image")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @formatter:on
public class MealImageEntity {

    // @formatter:off
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // meal_image_id を自動採番
    @Column(name = "meal_image_id")
    // @formatter:on
    private Integer mealImageId;

    @Column(name = "meal_id", nullable = false)
    private Integer mealId;

    @Column(name = "file_path", length = 100)
    private String filePath;

    @Column(name = "file_name", length = 20)
    private String fileName;

    @Column(name = "created_by", nullable = false, length = 36)
    private String createdBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_by", nullable = false, length = 36)
    private String updatedBy;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deleted", nullable = false)
    private Integer deleted;
}
