package com.example.tripease.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name="driverInfo")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driverId;

    @Column(name="FullName")
    private String name;
    private int age;

    @Column(unique = true, nullable = true)
    private String emailId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="driverId")
    List<Booking> bookings = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cabId")
    private Cab cab;
}
