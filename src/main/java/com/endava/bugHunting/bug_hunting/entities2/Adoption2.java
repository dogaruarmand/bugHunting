package com.endava.bugHunting.bug_hunting.entities2;

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

@Entity
@Table(name = "Adoptions2")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Adoption2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pet_id", nullable = false)
    private String petId;

    @Column(name = "user_id", nullable = false)
    private String userId;

}
