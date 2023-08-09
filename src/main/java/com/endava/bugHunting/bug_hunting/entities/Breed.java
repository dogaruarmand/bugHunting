package com.endava.bugHunting.bug_hunting.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "BREEDS",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"BREED"},
                        name = "UK_BREED"
                )
        }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "BREED", nullable = false)
    private String breed;

    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;
}
