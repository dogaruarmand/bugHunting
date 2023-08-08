package com.endava.bugHunting.bug_hunting.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "CATEGORIES",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"CATEGORY"},
                        name = "UK_CATEGORIES"
                )
        }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CATEGORY", nullable = false)
    private String category;
}
