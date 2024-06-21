package com.example.demo.db.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity
@ToString
@Table(name = "countries")
public class CountryEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "capital", nullable = false)
    private String capital;

    @Column(name = "population", nullable = false)
    private int population;

    @Column(name = "currency", nullable = false)
    private String currency;
}
