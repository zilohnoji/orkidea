package com.donatoordep.orkidea.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donatoordep.orkidea.dto.ProductDTO;
import com.donatoordep.orkidea.services.ProductService;

@RestController
@RequestMapping("/product/v1")
public class ProductController {

	@Autowired
	ProductService service;

	@PostMapping(path = "/", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO dto) {
		return ResponseEntity.ok().body(service.insert(dto));
	}

	@GetMapping(path = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ProductDTO>> getAll() {
		return ResponseEntity.ok().body(service.getAll());
	}

}
