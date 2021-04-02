package ru.mart.shop.controller;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ru.mart.shop.model.Role;
import ru.mart.shop.model.User;
import ru.mart.shop.repository.UserRepository;

@Controller
public class RegistrationController {

	private UserRepository userRepo;
	
	@Autowired	
	public RegistrationController(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@GetMapping("/registration")
	public String regirstration() {
		return "registration";
	}
	
	@PostMapping("/registration")
	public String addUser(User user, Model model) {
		User userFromDb = userRepo.findByUsername(user.getUsername());
		
		if(userFromDb != null) {		
			model.addAttribute("message", "User already exists");
			return "registration";
		}
		
		user.setActive(true);
		user.setRoles(Stream.of(Role.USER).collect(Collectors.toSet()));
		return "redirect:/login";
		}
	
	
	
}
