package com.donatoordep.orkidea.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.donatoordep.orkidea.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
