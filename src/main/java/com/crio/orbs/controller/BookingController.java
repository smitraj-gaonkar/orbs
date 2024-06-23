package com.crio.orbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.crio.orbs.entity.Booking;
import com.crio.orbs.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/hotel/{hotelId}")
    public ResponseEntity<Booking> bookRoom(@PathVariable Long hotelId, @RequestParam String userEmail) {
        Booking booking = bookingService.bookRoom(hotelId, userEmail);
        return ResponseEntity.ok(booking);
    }

    @PreAuthorize("hasRole('HOTEL_MANAGER')")
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok().build();
    }
}
