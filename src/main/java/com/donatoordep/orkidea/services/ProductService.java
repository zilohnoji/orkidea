package com.donatoordep.orkidea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.donatoordep.orkidea.dto.ProductDTO;
import com.donatoordep.orkidea.entities.Product;
import com.donatoordep.orkidea.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService implements GenericService<Product, Long, ProductDTO> {

	@Autowired
	ProductRepository repository;

	@Override
	public JpaRepository<Product, Long> repository() {
		return repository;
	}

}
