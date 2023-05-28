package com.donatoordep.orkidea.dto;

import com.donatoordep.orkidea.entities.Client;
import com.donatoordep.orkidea.gender.Gender;
import com.donatoordep.orkidea.utils.ConversibleContract;

public class ClientDTO implements ConversibleContract<Client> {

	public Long id;
	public String email;
	public Gender gender;
	private String password;
	private String cpf;
	private Double balance;

	public ClientDTO() {
	}

	public ClientDTO(Client client) {
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
