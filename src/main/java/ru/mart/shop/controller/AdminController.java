package ru.mart.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.mart.shop.model.User;
import ru.mart.shop.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private UserRepository userRepo;
	
	@Autowired
	public AdminController(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@GetMapping("/users")
	public String getUsers(Model model) {
		List<User> userList = userRepo.findAll();
		model.addAttribute("users", userList);
		return "users";
	}
	
	@GetMapping("/user/{user}")
	public String editUser(@PathVariable User user, Model model) {
		model.addAttribute("user", user);
		return "userEdit";		
	}
}
