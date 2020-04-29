package com.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.products.Shirts;

@Repository
public interface BookRepo extends JpaRepository<Shirts, Integer> {

}