package com.donatoordep.orkidea.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donatoordep.orkidea.dto.ClientDTO;
import com.donatoordep.orkidea.services.ClientService;

@RestController
@RequestMapping("/client/v1")
public class ClientController {

	@Autowired
	private ClientService service;

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClientDTO> findById(@PathVariable(name = "id") Long id) {
		return (service.findById(id) != null) ? ResponseEntity.status(HttpStatus.ACCEPTED).body(service.findById(id))
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping(path = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<ClientDTO>> getAll() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getAll());
	}

	@PostMapping(path = "/insert", produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
	}
}
