package com.donatoordep.orkidea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donatoordep.orkidea.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
