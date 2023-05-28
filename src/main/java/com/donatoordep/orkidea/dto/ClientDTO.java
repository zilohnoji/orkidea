package com.donatoordep.orkidea.dto;

import com.donatoordep.orkidea.entities.Client;
import com.donatoordep.orkidea.utils.ConversibleContract;
import com.donatoordep.orkidea.utils.Gender;

public class ClientDTO implements ConversibleContract<Client> {

	public Long id;
	public String email;
	public Gender gender;
	public String name;
	private String password;
	private String cpf;
	private Double balance;

	public ClientDTO() {
	}

	public ClientDTO(Client client) {
		this.email = client.getEmail();
		this.name = client.getName();
		this.cpf = client.getCpf();
		this.id = client.getId();
		this.balance = client.getBalance();
		this.gender = client.getGender();
		this.password = client.getPassword();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public Client fromConvert() {
		return new Client(this);
	}

}
