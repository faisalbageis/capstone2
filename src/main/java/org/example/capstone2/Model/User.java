package org.example.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,length = 100)
    @NotEmpty(message = "user name can not be empty")
    @Size(max = 100 ,message = "user name length must be less than 100")
    private String fullName;

    @Column(nullable = false,unique = true)
    @Email(message = "in valid email")
    @NotEmpty(message = "email can not be empty")
    @Size(max = 100,message = "email length must be less than 100")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "password can not be empty")
    @Size(min = 8,max = 255,message = "password length must be between  255 and 8")
    private String password;

    @Column(nullable = false)
    @Pattern(regexp = "active|Suspended|Blocked",message = "status must be ether active or Suspended or Blocked")
    private String status="active";

    @Column(nullable = false,unique = true)
    @NotEmpty(message = "phone number can not be empty")
    @Size(min = 10,max = 10,message = "phone number length must be 10")
    @Pattern(regexp = "05[0-9]{8}" ,
            message ="phoneNumber must be valid Saudi phone number" )
    private String phoneNumber;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private LocalDate createdAt;
}
