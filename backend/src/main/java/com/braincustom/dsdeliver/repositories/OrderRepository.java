package com.braincustom.dsdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.braincustom.dsdeliver.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
