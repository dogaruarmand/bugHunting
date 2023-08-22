package com.endava.bugHunting.bug_hunting.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PETS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "AGE", nullable = false)
    private Integer age;

    @Column(name = "OWNERS_NAME", nullable = false)
    private String ownersName;

    @Column(name = "OWNERS_PHONE", nullable = false)
    private String ownersPhone;

    @Column(name = "OWNERS_EMAIL", nullable = false)
    private String ownersEmail;

    @Column(name = "GENDER", nullable = false)
    private String gender;

    @Column(name = "ADDOPTED", nullable = false)
    private String addopted; //yes/no

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category type;

    @ManyToOne
    @JoinColumn(name = "breed_id", nullable = false)
    private Breed breed;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "foster_user_id")
    private User foster;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
