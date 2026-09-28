package org.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "legal_Case")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "requestId can not be empty")
    private Integer requestId;

    @Column(nullable = false)
    @NotNull(message = "userId can not be empty")
    private Integer userId;

    @Column(nullable = false)
    @NotNull(message = "lawyerId can not be empty")
    private Integer lawyerId;

    @Column(nullable = false, length = 100)
    @NotEmpty(message = "case type can not be empty")
    @Size(max = 100, message = "case type length must be less than 100")
    private String caseType;

    @Column(length = 1000)
    @Size(max = 1000, message = "description length must be less than 1000")
    private String description;

    @Column(nullable = false)
    @NotEmpty(message = "status can not be empty")
    @Pattern(
            regexp = "Open|Closed",
            message = "status must be either Open, In Progress or Closed"
    )
    private String status = "Open";
}