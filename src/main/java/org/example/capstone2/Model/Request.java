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
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotNull(message = "userId can not be empty")
    private Integer userId;

    @Column(nullable = false)
    @NotNull(message = "lawyer id can not be empty")
    private Integer lawyerId;

    @Column(nullable = false, length = 100)
    @NotEmpty(message = "case type can not be empty")
    @Size(max = 100, message = "case type length must be less than 100")
    private String caseType;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    @Pattern(
            regexp = "Pending|Accepted|Rejected|Cancelled",
            message = "status must be either Pending, Accepted, Rejected or Cancelled"
    )
    private String status;

    @Column(nullable = false, length = 1000)
    @NotEmpty(message = "description can not be empty")
    @Size(max = 1000, message = "description length must be less than 1000")
    private String description;
}