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
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    @NotEmpty(message = "user name can not be empty")
    @Size(max = 100, message = "user name length must be less than 100")
    private String fullName;

    @Column(nullable = false, unique = true)
    @Email(message = "in valid email")
    @NotEmpty(message = "email can not be empty")
    @Size(max = 100, message = "email length must be less than 100")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "password can not be empty")
    @Size(min = 8, max = 255, message = "password length must be between 255 and 8")
    private String password;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;
}