package com.donatoordep.orkidea.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.donatoordep.orkidea.dto.ClientDTO;
import com.donatoordep.orkidea.services.ClientService;
import com.donatoordep.orkidea.services.ProductService;

@RestController
@RequestMapping("/client/v1")
public class ClientController {

	@Autowired
	private ClientService service;

	@Autowired
	private ProductService productService;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClientDTO> findById(@PathVariable(name = "id") Long id) {
		return (service.findById(id) != null) ? ResponseEntity.status(HttpStatus.OK).body(service.findById(id))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping(path = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ClientDTO>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}

	@PostMapping(path = "/", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
	}

	@PutMapping(path = "/", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO dto) {
		return (dto.getId() != null) ? ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id) {
		service.delete(id);
		return (service.findById(id) != null) ? ResponseEntity.status(HttpStatus.OK).build()
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PutMapping(path = "/buy/{id}")
	public ResponseEntity<ClientDTO> addProductCart(@PathVariable(name = "id") Long id,
			@RequestParam(name = "product-id") Long productId) {

		ClientDTO dto = service.findById(id);
		dto.getProductList().add(productService.findById(productId).fromConvert());

		service.insert(dto);

		System.out.println(dto);
		return ResponseEntity.ok().body(dto);
	}
}
