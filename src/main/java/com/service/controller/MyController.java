package com.service.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.service.DataBaseServices;
import com.service.ImageUploader;
import com.service.LoginModel;
import com.service.ProductDetails;
import com.service.accountdetails.AddItems;
import com.service.accountdetails.Cart;
import com.service.accountdetails.LoginAccount;
import com.service.mailservices.MailSenderUtility;
import com.service.products.Shirts;
import com.service.products.Electronics;
import com.service.products.Sports;
import com.service.products.jewellery;
import com.service.utility.ControllerUtility;

@Controller
@RequestMapping(value = "/account")
public class MyController {

	@Autowired
	private DataBaseServices service;

	@Autowired
	private LoginModel login;

	@Autowired
	private MailSenderUtility mailSender;

	@Autowired
	private ImageUploader uploader;
	@Autowired
	private ControllerUtility utlity;

	@GetMapping("/home")
	public String homePage(Model model) {
		List<ProductDetails> product = service.getAllproduct();
		List<Electronics> electronics = service.getAllElectronics();
		List<Sports> sports = service.getAllSports();
		List<jewellery> jewellery = service.getAlljewellery();
		List<Shirts> books = service.getAllBooks();
		model.addAttribute("product", product);
		model.addAttribute("electronics", electronics);
		model.addAttribute("sports", sports);
		model.addAttribute("jewellery", jewellery);
		model.addAttribute("books", books);
		return "homepage";
	}

	@PostMapping("/login")
	public String myPage(@RequestParam("useremail") String useremail, @RequestParam("password") String password,
			Model model, HttpSession session) {
		LoginAccount account = new LoginAccount();
		account.setUseremail(useremail);
		account.setPassword(password);
		if (login.login(account) != null) {
			List<ProductDetails> product = service.getAllproduct();
			List<Electronics> electronics = service.getAllElectronics();
			List<Sports> sports = service.getAllSports();
			List<jewellery> jewellery = service.getAlljewellery();
			List<Shirts> books = service.getAllBooks();
			session.setAttribute("product", product);
			session.setAttribute("electronics", electronics);
			session.setAttribute("sports", sports);
			session.setAttribute("jewellery", jewellery);
			session.setAttribute("books", books);
			account = login.login(account);
			session.setAttribute("user", account);
			session.setAttribute("user", account);
			return "homepage";
		}
		return "invalid username or password";
	}

	@PostMapping("/signup")
	public String signUp(@RequestParam("useremail") String useremail, @RequestParam("password") String password,
			@RequestParam("username") String username, @RequestParam("userphone") String userphone, Model model)
			throws MessagingException {
		LoginAccount account = new LoginAccount();
		account.setUseremail(useremail);
		account.setPassword(password);
		account.setUsername(username);
		account.setUserphone(userphone);
		login.createAccount(account);
		mailSender.sendEmail(account.getUsername(), account.getUseremail());
		return "homepage";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "homepage";

	}

	@GetMapping("/forget")
	public String forgetPassword() {
		return "forgetpassword";

	}

	@GetMapping("/userpage")
	public String userHomePage() {
		return "userpage";

	}

	@GetMapping("/addcart/{id}/{cat}")
	public void addTOCart(@PathVariable("id") String id, @PathVariable("cat") String cat, HttpSession session) {
		Cart cart = new Cart();
		LoginAccount login;
		login = (LoginAccount) session.getAttribute("user");
		cart.setProductId(id);
		cart.setAccount(login);
		cart.setUserId(login.getUseremail());
		service.addTOCart(cart);
		System.out.println("hello" + cat);

	}

	@GetMapping("/orders/{id}")
	public String userOrders(@PathVariable("id") String id, Model model) {
		List<Cart> listOfCarts = service.geAllItems(id);
		model.addAttribute("listOfCarts", listOfCarts);
		return "userorders";
	}

	@GetMapping("/category/{id}")
	public String category(@PathVariable("id") String id, Model model, HttpSession session) {

		if (id.equals("electronics")) {
			List<Electronics> electronics = service.getAllElectronics();
			model.addAttribute("electronics", electronics);
		}
		if (id.equals("sports")) {
			List<Sports> sports = service.getAllSports();
			model.addAttribute("sports", sports);
		}

		if (id.equals("jewellery")) {
			List<jewellery> jewellery = service.getAlljewellery();
			model.addAttribute("jewellery", jewellery);
		}
		if (id.equals("shirts")) {
			List<Shirts> shirts = service.getAllBooks();
			model.addAttribute("shirts", shirts);
		}

		return "categoryPage";

	}

	@PostMapping("/additems")
	public String addItems(@RequestParam("file") MultipartFile file, @RequestParam("price") String price,
			@RequestParam("discription") String discription, @RequestParam("Category") String category,
			@RequestParam("productName") String productName) {

		String fileName = uploader.imageUploader(file, "file");
		AddItems addItems = new AddItems();
		addItems.setDiscription(discription);
		addItems.setPrice(Integer.parseInt(price));
		addItems.setProductName(fileName);
		addItems.setQuantity(10);
		addItems.setCategory(category);

		if (category.equals("electronics")) {
			Electronics electronics = new Electronics();
			utlity.serialized(addItems, electronics);
			service.insert(electronics);

		}
		if (category.equals("sports")) {
			Sports sports = new Sports();
			utlity.serialized(addItems, sports);
			service.insert(sports);
		}
		if (category.equals("jewellary")) {
			jewellery jewellery = new jewellery();
			utlity.serialized(addItems, jewellery);
			service.insert(jewellery);
		}
		if (category.equals("shirts")) {
			Shirts shirts = new Shirts();
			utlity.serialized(addItems, shirts);
			service.insert(shirts);
		}

		return "userpage";

	}

}