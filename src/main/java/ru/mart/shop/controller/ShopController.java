package ru.mart.shop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.mart.shop.model.Product;
import ru.mart.shop.repository.ProductRepository;

@Controller
@RequestMapping("/shop")
public class ShopController {
	
	@Autowired()
	private ProductRepository productRepo;

	//приветствие
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "Shop")
							String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}	
	
	//главная страница
		@GetMapping
	public String index(Model model) {
		model.addAttribute("products", productRepo.findAll());		
		return "main";
	}
	
	//страница создания товара
	@GetMapping("/new")
	public String newPerson(Model model) {
		model.addAttribute("product",  new Product());
		return "new";
	}	

	//добавление нового товара
	@PostMapping("/new")
	public String add(@RequestParam("name") String name, 
							@RequestParam("cost") Long cost,
								@RequestParam("party") String party,
														Model model) {
		Product product = new Product();
		product.setName(name);
		product.setCost(cost);
		product.setParty(party);
		model.addAttribute("product", product);
		productRepo.save(product);
		return "redirect:/shop";
	}
	
	//выбор конктретного товара
	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		getProduct(id, model);
		return "show";
	}
	
	//страница для измений
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable("id") int id, Model model) {
		getProduct(id, model);
		return "edit";
	}
	
	//обновление товара
	@PostMapping("/{id}")
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
	
	//поиск товара
	@PostMapping("/filter")
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
	

	
	
}
