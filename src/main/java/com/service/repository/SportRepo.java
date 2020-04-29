package com.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.products.Sports;
import com.service.products.jewellery;
@Repository
public interface SportRepo extends JpaRepository<Sports, Integer> {

}