package ru.mart.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import ru.mart.shop.model.User;

@Service
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
}
