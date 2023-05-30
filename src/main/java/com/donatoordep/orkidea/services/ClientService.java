package com.donatoordep.orkidea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.donatoordep.orkidea.dto.ClientDTO;
import com.donatoordep.orkidea.dto.ProductDTO;
import com.donatoordep.orkidea.entities.Client;
import com.donatoordep.orkidea.repositories.ClientRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClientService implements GenericService<Client, Long, ClientDTO> {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private ProductService serviceProduct;

	@Override
	public JpaRepository<Client, Long> repository() {
		return repository;
	}

	public ClientDTO addCart(Long productId, Long clientId, Integer quantity) {
		ClientDTO dtoClient = repository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException())
				.fromConvert();
		if (!dtoClient.getProductList().contains(serviceProduct.findById(productId).fromConvert())) {
			ProductDTO product = serviceProduct.findById(productId);
			product.setQuantity(quantity);
			dtoClient.getProductList().add(product.fromConvert());
			repository.save(dtoClient.fromConvert());
			return dtoClient;
		} else {
			return null;
		}
	}

	public ClientDTO buyProduct(Long productId, Long clientId) {
		ClientDTO clientObj = repository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException())
				.fromConvert();

		ProductDTO product = serviceProduct.findById(productId);

		if (clientObj.getBalance() >= product.getPrice()
				&& clientObj.getProductList().contains(product.fromConvert())) {
			clientObj.getProductList().removeIf(x -> x.equals(product.fromConvert()));
			clientObj.buy(product.getPrice(), product.getQuantity());
			repository.save(clientObj.fromConvert());

			return clientObj;
		} else {
			return null;
		}
	}

}
