package com.endava.bugHunting.bug_hunting.entities2;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "CATEGORIES2",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"CATEGORY"},
                        name = "UK_CATEGORIES2"
                )
        }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CATEGORY", nullable = false)
    private String category;
}
