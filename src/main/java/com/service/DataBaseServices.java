package com.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.service.accountdetails.Cart;
import com.service.accountdetails.LoginAccount;
import com.service.products.Shirts;
import com.service.products.Electronics;
import com.service.products.Sports;
import com.service.products.jewellery;
import com.service.repository.BookRepo;
import com.service.repository.CartRepo;
import com.service.repository.ElectronicsRepo;
import com.service.repository.JweRepo;
import com.service.repository.LoginRepo;
import com.service.repository.ProductRepo;
import com.service.repository.SportRepo;

@Component
public class DataBaseServices {

	@Autowired
	private LoginRepo repo;

	@Autowired
	private CartRepo cartrepo;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private BookRepo bookRepo;

	@Autowired
	private JweRepo jweRepo;

	@Autowired
	private SportRepo sportRepo;

	@Autowired
	private ElectronicsRepo electronicsRepo;

	public void insert(LoginAccount account) {
		repo.save(account);
	}

	public LoginAccount getData(String id) {
		return repo.getOne(id);
	}

	public void addTOCart(Cart cart) {
		cartrepo.save(cart);
	}

	public List<Cart> geAllItems(String id) {
		List<Cart> list = cartrepo.findByuserId(id);
		return list;

	}

	public List<ProductDetails> getAllproduct() {
		List<ProductDetails> list = productRepo.findAll();
		return list;
	}

	public List<Electronics> getAllElectronics() {
		List<Electronics> list = electronicsRepo.findAll();
		return list;
	}

	public List<Shirts> getAllBooks() {
		List<Shirts> list = bookRepo.findAll();
		return list;
	}

	public List<Sports> getAllSports() {
		List<Sports> list = sportRepo.findAll();
		return list;
	}

	public List<jewellery> getAlljewellery() {
		List<jewellery> list = jweRepo.findAll();
		return list;
	}

}
