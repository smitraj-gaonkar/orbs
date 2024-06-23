package com.crio.orbs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.orbs.entity.Booking;
import com.crio.orbs.entity.Hotel;
import com.crio.orbs.entity.User;
import com.crio.orbs.repository.BookingRepository;
import com.crio.orbs.repository.HotelRepository;
import com.crio.orbs.repository.UserRepository;

import java.time.LocalDateTime;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserRepository userRepository;

    public Booking bookRoom(Long hotelId, String userEmail) {
        Hotel hotel = hotelRepository.findById(hotelId)
            .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + hotelId));
        
        if (hotel.getAvailableRooms() <= 0) {
            throw new RuntimeException("No rooms available");
        }
        
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new RuntimeException("User not found with email: " + userEmail));

        hotel.setAvailableRooms(hotel.getAvailableRooms() - 1);
        hotelRepository.save(hotel);

        Booking booking = new Booking();
        booking.setHotel(hotel);
        booking.setUser(user);
        booking.setBookingDate(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));

        Hotel hotel = booking.getHotel();
        hotel.setAvailableRooms(hotel.getAvailableRooms() + 1);
        hotelRepository.save(hotel);

        bookingRepository.delete(booking);
    }
}