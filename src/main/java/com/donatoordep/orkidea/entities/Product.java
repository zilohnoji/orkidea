package com.donatoordep.orkidea.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.donatoordep.orkidea.dto.ProductDTO;
import com.donatoordep.orkidea.utils.ConversibleContract;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Product implements Serializable, ConversibleContract<ProductDTO> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	public String name;
	public Double value;
	public String description;

	@ManyToMany(mappedBy = "productList")
	public List<Client> client = new ArrayList<>();

	public Product() {
	}

	public Product(ProductDTO dto) {
		this.id = dto.getId();
		this.name = dto.getName();
		this.description = dto.getDescription();
		this.value = dto.getValue();
		this.client = dto.getClient();
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

	public void setClient(List<Client> client) {
		this.client = client;
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
	public int hashCode() {
		return Objects.hash(description, id, name, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", value=" + value + ", description=" + description + "]";
	}

	@Override
	public ProductDTO fromConvert() {
		return new ProductDTO(this);
	}

}
