package com.donatoordep.orkidea.entities;

import java.io.Serializable;
import java.util.Objects;

import com.donatoordep.orkidea.dto.ClientDTO;
import com.donatoordep.orkidea.utils.ConversibleContract;
import com.donatoordep.orkidea.utils.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Client implements Serializable, ConversibleContract<ClientDTO> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;

	@Column(nullable = false, unique = true, length = 50)
	public String email;

	@Column(nullable = false, unique = false, length = 1)
	public Gender gender;

	@Column(nullable = false, unique = false, length = 100)
	public String name;

	@Column(nullable = false, unique = false, length = 100)
	private String password;

	@Column(nullable = true, unique = true, length = 14)
	private String cpf;

	@Column(nullable = false, unique = false)
	private Double balance;

	public Client() {
	}

	public Client(ClientDTO dto) {
		this.email = dto.getEmail();
		this.name = dto.getName();
		this.cpf = dto.getCpf();
		this.id = dto.getId();
		this.balance = dto.getBalance();
		this.gender = dto.getGender();
		this.password = dto.getPassword();
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
	public int hashCode() {
		return Objects.hash(cpf, email, id, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", email=" + email + ", password=" + password + ", cpf=" + cpf + "]";
	}

	@Override
	public ClientDTO fromConvert() {
		return new ClientDTO(this);
	}

}
