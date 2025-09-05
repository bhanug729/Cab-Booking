package com.example.tripease.dto.response;

import com.example.tripease.Enum.TripStatus;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookingResponse {
    private String pickup;
    private String destination;
    private double tripDistanceInKM;
    private TripStatus tripStatus;
    private double billAmount;
    private Date bookedAt;
    private Date lastUpdate;
    private CustomerResponse customer;
    private CabResponse cab;
}
