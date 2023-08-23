package com.endava.bugHunting.bug_hunting.entities2;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "BREEDS2",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"BREED"},
                        name = "UK_BREED2"
                )
        }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Breed2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "BREED", nullable = false)
    private String breed;

    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private Category2 category;
}
