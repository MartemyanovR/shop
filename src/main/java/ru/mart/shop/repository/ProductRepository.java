package ru.mart.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import ru.mart.shop.model.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
	
	List<Product> findByName(String name);
	
	@Modifying
	@Transactional()
	@Query("UPDATE Product SET name=?1, party=?2, cost=?3 WHERE id=?4 ")
	void updateProduct(String name, String party, Long cost, Integer id);
	

}
