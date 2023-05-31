package com.donatoordep.orkidea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.donatoordep.orkidea.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	@Query("SELECT u FROM Client u WHERE u.email = ?1")
	Client findClientByEmail(String email);
}
