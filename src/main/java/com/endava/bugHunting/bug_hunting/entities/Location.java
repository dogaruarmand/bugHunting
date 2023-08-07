package com.endava.bugHunting.bug_hunting.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "location")
@Data
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "location_gen")
    @SequenceGenerator(name = "location_gen", sequenceName = "location_seq")
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long id;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

}
