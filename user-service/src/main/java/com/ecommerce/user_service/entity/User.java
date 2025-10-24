package com.ecommerce.user_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @SequenceGenerator(
            name = "generator",
            sequenceName = "sequencegenerator"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequencegenerator")
    private Long id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
}
