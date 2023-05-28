package com.donatoordep.orkidea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.donatoordep.orkidea.dto.ClientDTO;
import com.donatoordep.orkidea.entities.Client;
import com.donatoordep.orkidea.repositories.ClientRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClientService implements GenericService<Client, Long, ClientDTO> {

	@Autowired
	private ClientRepository repository;

	@Override
	public JpaRepository<Client, Long> repository() {
		return repository;
	}

}
