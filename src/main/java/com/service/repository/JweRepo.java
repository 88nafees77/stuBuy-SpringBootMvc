package com.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.accountdetails.LoginAccount;
import com.service.products.jewellery;

@Repository
public interface JweRepo extends JpaRepository<jewellery, Integer> {

}
