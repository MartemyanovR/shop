package ru.mart.shop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.mart.shop.model.Product;
import ru.mart.shop.model.User;
import ru.mart.shop.repository.ProductRepository;

@Controller
@RequestMapping
public class ShopController {
	
	@Autowired(required=true)
	private ProductRepository productRepo;

	//приветствие
	@GetMapping
	public String greeting( Model model) {
		
		return "greeting";
	}	
	
	//главная страница
		@GetMapping("/shop")
	public String index(Model model) {
		model.addAttribute("products", productRepo.findAll());		
		return "main";
	}
	
	//страница создания товара
	@GetMapping("/shop/new")
	public String newPerson(Model model) {
		model.addAttribute("product",  new Product());
		return "new";
	}	

	//добавление нового товара
	@PostMapping("/shop/new")
	public String add(@AuthenticationPrincipal User user,
							@RequestParam("name") String name, 
											@RequestParam("cost") Long cost,
												@RequestParam("party") String party,
																		Model model) {
		Product product = new Product();
		product.setName(name);
		product.setCost(cost);
		product.setParty(party);
		product.setAuthor(user);
		model.addAttribute("product", product);
		productRepo.save(product);
		return "redirect:/shop";
	}
	
	//выбор конктретного товара
	@GetMapping("/shop/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		getProduct(id, model);
		return "show";
	}
	
	//страница для измений
	@GetMapping("/shop/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		getProduct(id, model);
		return "edit";
	}
	
	//вспомогательный метод поиска продукта по id
		private void getProduct(int id, Model model) {
			Product product;
			Optional<Product> productOptional = productRepo.findById(id);
			if(productOptional.isPresent()) {
				product = productOptional.get();
				model.addAttribute("product", product);
			} else {
				System.out.println("Product not found");
			}		
		}	
	
	//обновление товара
	@PostMapping("/shop/{id}")
	public String  update(@PathVariable("id") int id,
								@RequestParam("name") String name, 
									@RequestParam("cost") Long cost,
										@RequestParam("party") String party,
																	Model model) {
		Product product = new Product();
		product.setId(id);
		product.setName(name);
		product.setCost(cost);
		product.setParty(party);
		model.addAttribute("product", product);
		if(product != null) {
			productRepo.updateProduct(name, party, cost, id);
		}
		return "redirect:/shop";
	}	
	
	//поиск товара
	@PostMapping("/shop/filter")
	public String filter(@RequestParam("filter") String filter,  Model model) {
		Iterable<Product> products;
		if(filter != null && !(filter.isEmpty())) {
			products = productRepo.findByName(filter);
		} else {
			products = productRepo.findAll();
		}
		model.addAttribute("products", products);
		return "main";
	}
	
	@GetMapping("/shop/delete")
	public String delete(@RequestParam("id") int id) {
		productRepo.deleteById(id);
		return "redirect:/shop";
	}
	
	
}
