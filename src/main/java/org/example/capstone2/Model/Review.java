package org.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private Integer caseId;

    @Column(nullable = false)
    @NotNull(message = "rating can not be empty")
    @DecimalMin(value = "1.0", message = "rating must be at least 1")
    @DecimalMax(value = "5.0", message = "rating must be at most 5")
    private Double rating;

    @Column(length = 1000)
    @Size(max = 1000, message = "comment length must be less than 1000")
    private String comment;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;
}