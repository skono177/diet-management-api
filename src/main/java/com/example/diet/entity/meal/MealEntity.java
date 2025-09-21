package com.example.diet.entity.meal;

import java.time.LocalDate;
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
@Table(schema = "diet", name = "t_meal")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @formatter:on
public class MealEntity {

    // @formatter:off
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    // @formatter:on
    private Integer mealId;

    @Column(name = "register_by", nullable = false, length = 36)
    private String registerBy;

    @Column(name = "register_date")
    private LocalDate registerDate;

    @Column(name = "meal_type")
    private Integer mealType;

    @Column(name = "calorie")
    private Integer calorie;

    @Column(name = "comment", length = 100)
    private String comment;

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
