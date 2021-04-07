package ru.mart.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id; 
	@Column(name = "name")
	private String name;
	@Column(name = "cost")
	private Long cost;
	@Column(name = "party")
	private String party;	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User author;	

	public Product() { }	
	
	public Product(String name, Long cost, String party, User author) {
		super();
		this.name = name;
		this.cost = cost;
		this.party = party;
		this.author = author;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public Long getCost() {
		return cost;
	}
	public void setCost(Long cost) {
		this.cost = cost;
	}
	
	public String getAuthorName() {
		return (author != null) ? author.getUsername() : "<no name>";
	}
	
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", party=" + party + ", cost=" + cost + "]";
	}
	

}
