package com.example.tripease.service;

import com.example.tripease.dto.request.BookingRequest;
import com.example.tripease.dto.response.BookingResponse;
import com.example.tripease.exception.CabUnavailableException;
import com.example.tripease.exception.CustomerNotFoundException;
import com.example.tripease.model.Booking;
import com.example.tripease.model.Cab;
import com.example.tripease.model.Customer;
import com.example.tripease.model.Driver;
import com.example.tripease.repository.BookingRepository;
import com.example.tripease.repository.CabRepository;
import com.example.tripease.repository.CustomerRepository;
import com.example.tripease.repository.DriverRepository;
import com.example.tripease.transformer.BookingTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CabRepository cabRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public BookingResponse bookCab(BookingRequest bookingRequest, int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if(optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException("Invalid customer id!!");
        }
        Customer customer = optionalCustomer.get();

        Cab avalaibleCab = cabRepository.getAvailableCabRandomly();
        if (avalaibleCab==null) {
            throw new CabUnavailableException("Sorry, No cabs available!!");
        }
        avalaibleCab.setAvailable(false);

        Booking booking = BookingTransformer.bookingRequestToBooking(bookingRequest, avalaibleCab.getPerKmRate());
        Booking savedBooking = bookingRepository.save(booking);

        customer.getBookings().add(savedBooking);
        Customer savedCustomer = customerRepository.save(customer);

        Driver driver = driverRepository.getDriverByCabId(avalaibleCab.getCabId());
        driver.getBookings().add(savedBooking);
        Driver savedDriver = driverRepository.save(driver);

        sendMail(customer);
        return BookingTransformer.bookingToBookingResponse(savedBooking, savedCustomer, avalaibleCab, savedDriver);
    }

    private void sendMail(Customer customer) {
        String text = "Congrats! " + customer.getName() + ", your cab has been booked.";

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("bhanug729@gmail.com");
        simpleMailMessage.setTo(customer.getEmailId());
        simpleMailMessage.setSubject("Cab Booked!");
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }
}
