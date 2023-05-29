package com.donatoordep.orkidea.dto;

import java.util.List;

import com.donatoordep.orkidea.entities.Client;
import com.donatoordep.orkidea.entities.Product;
import com.donatoordep.orkidea.utils.ConversibleContract;

public class ProductDTO implements ConversibleContract<Product> {
	public Long id;

	public String name;
	public Double value;
	public String description;
	public List<Client> client;

	public ProductDTO() {
	}

	public ProductDTO(Product entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.description = entity.getDescription();
		this.value = entity.getValue();
		this.client = entity.getClient();
	}

	public List<Client> getClient() {
		return client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Product fromConvert() {
		return new Product(this);
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", value=" + value + ", description=" + description
				+ ", client=" + client + "]";
	}

}
