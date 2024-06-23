package com.crio.orbs.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.orbs.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}