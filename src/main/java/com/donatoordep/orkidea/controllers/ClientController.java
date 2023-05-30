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

@RestController
@RequestMapping("/client/v1")
public class ClientController {

	@Autowired
	private ClientService serviceClient;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClientDTO> findById(@PathVariable(name = "id") Long id) {
		return (serviceClient.findById(id) != null)
				? ResponseEntity.status(HttpStatus.OK).body(serviceClient.findById(id))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping(path = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ClientDTO>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(serviceClient.getAll());
	}

	@PostMapping(path = "/", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(serviceClient.insert(dto));
	}

	@PutMapping(path = "/", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClientDTO> update(@RequestBody ClientDTO dto) {
		return (dto.getId() != null) ? ResponseEntity.status(HttpStatus.CREATED).body(serviceClient.insert(dto))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id) {
		serviceClient.delete(id);
		return (serviceClient.findById(id) != null) ? ResponseEntity.status(HttpStatus.OK).build()
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	@PostMapping(path = "/add/{id}/{product_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClientDTO> addProductCart(@PathVariable(name = "id") Long clientId,
			@PathVariable(name = "product_id") Long productId, @RequestParam(name = "quantity") Integer quantity) {
		return (serviceClient.addCart(productId, clientId, quantity) != null)
				? ResponseEntity.ok().body(serviceClient.addCart(productId, clientId, quantity))
				: ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	@PutMapping(path = "/buy/{id}/{product_id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClientDTO> buyProduct(@PathVariable(name = "id") Long idClient,
			@PathVariable(name = "product_id") Long idProduct) {
		return (serviceClient.buyProduct(idProduct, idClient) != null)
				? ResponseEntity.ok().body(serviceClient.buyProduct(idProduct, idClient))
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
