package com.example.tripease.transformer;

import com.example.tripease.Enum.TripStatus;
import com.example.tripease.dto.request.BookingRequest;
import com.example.tripease.dto.response.BookingResponse;
import com.example.tripease.model.Booking;
import com.example.tripease.model.Cab;
import com.example.tripease.model.Customer;
import com.example.tripease.model.Driver;

public class BookingTransformer {
    public static Booking bookingRequestToBooking(BookingRequest bookingRequest, double perKmRate) {
        return Booking.builder()
                .pickup(bookingRequest.getPickup())
                .destination(bookingRequest.getDestination())
                .tripDistanceInKM(bookingRequest.getTripDistanceInKM())
                .tripStatus(TripStatus.IN_PROGRESS)
                .billAmount(bookingRequest.getTripDistanceInKM()*perKmRate)
                .build();
    }

    public static BookingResponse bookingToBookingResponse(Booking booking, Customer customer, Cab cab, Driver driver) {
        return BookingResponse.builder()
                .pickup(booking.getPickup())
                .destination(booking.getDestination())
                .tripDistanceInKM(booking.getTripDistanceInKM())
                .tripStatus(booking.getTripStatus())
                .billAmount(booking.getBillAmount())
                .bookedAt(booking.getBookedAt())
                .lastUpdate(booking.getLastUpdate())
                .customer(CustomerTransformer.customertoCustomerResponse(customer))
                .cab(CabTransformer.cabToCabResponse(cab, driver))
                .build();
    }

}
