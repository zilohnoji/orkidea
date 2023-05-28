package com.donatoordep.orkidea.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donatoordep.orkidea.services.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientService service;

	@GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<String> init() {
		return ResponseEntity.ok().body("Hello spring REST api");
	}
}
