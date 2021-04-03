package ru.mart.shop.controller;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.mart.shop.model.Role;
import ru.mart.shop.model.User;
import ru.mart.shop.repository.UserRepository;

@Controller
@RequestMapping
public class RegistrationController {

	@Autowired(required=true)
	private UserRepository userRepo;
	
	
//	public RegistrationController(UserRepository userRepo) {
//		this.userRepo = userRepo;
//	}

	 @GetMapping("/login")
	 public String login() {
		 return "login";
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
		userRepo.save(user);
		return "redirect:/login";
		}
	
	
	
}
