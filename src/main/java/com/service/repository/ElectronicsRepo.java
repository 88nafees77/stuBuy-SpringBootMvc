package com.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.service.products.Electronics;

@Repository
public interface ElectronicsRepo extends JpaRepository<Electronics, Integer> {

}