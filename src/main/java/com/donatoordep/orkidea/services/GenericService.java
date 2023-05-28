package com.donatoordep.orkidea.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.donatoordep.orkidea.utils.ConversibleContract;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface GenericService<E extends ConversibleContract<DTO>, ID, DTO extends ConversibleContract<E>> {

	JpaRepository<E, ID> repository();

	default public DTO insert(DTO dto) {
		return repository().save(dto.fromConvert()).fromConvert();
	}

	default public DTO findById(ID id) {
		return repository().findById(id).orElseThrow(() -> new ResourceNotFoundException()).fromConvert();
	}
}
