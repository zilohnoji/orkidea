package com.donatoordep.orkidea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.donatoordep.orkidea.entities.Client;
import com.donatoordep.orkidea.repositories.ClientRepository;

@Service
public class ClientDetailsImplService implements UserDetailsService {

	@Autowired
	private ClientRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Client client = repository.findClientByEmail(email);
		if (client != null) {
			return new User(client.getEmail(), client.getPassword(), client.getAuthorities());
		} else {
			throw new UsernameNotFoundException(email);
		}
	}

}
