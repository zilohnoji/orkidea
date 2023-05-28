package com.donatoordep.orkidea.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@OneToOne(mappedBy = "cart")
	public Client client;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	public List<Product> products;

	public void addProduct(Product product) {
		products.add(product);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Product> getProducts() {
		return products;
	}

}
