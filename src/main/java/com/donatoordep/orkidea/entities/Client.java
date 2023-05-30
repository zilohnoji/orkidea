package com.donatoordep.orkidea.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.donatoordep.orkidea.dto.ClientDTO;
import com.donatoordep.orkidea.utils.ConversibleContract;
import com.donatoordep.orkidea.utils.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;

@Entity
public class Client implements ConversibleContract<ClientDTO>, UserDetails {

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
	private String pass;

	@Column(nullable = true, unique = true, length = 14)
	private String cpf;

	@Column(nullable = false, unique = false)
	private Double balance;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, unique = false)
	public LocalDateTime dateRegister = LocalDateTime.now();

	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@JoinTable(name = "product_client", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	public List<Product> productList = new ArrayList<>();

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "client_role", uniqueConstraints = @UniqueConstraint(columnNames = { "client_id",
			"role_id" }, name = "unique_role_client"), 
	joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id", table = "client", 
	foreignKey = @jakarta.persistence.ForeignKey(name = "client_fk", 
	value = ConstraintMode.CONSTRAINT)), inverseJoinColumns = @JoinColumn(name = "role_id", 
	referencedColumnName = "id", table = "role", foreignKey = @ForeignKey(name = "role_fk", value = ConstraintMode.CONSTRAINT)))
	private List<Role> roles;

	public Client() {
	}

	public Client(ClientDTO dto) {
		this.email = dto.getEmail();
		this.name = dto.getName();
		this.cpf = dto.getCpf();
		this.id = dto.getId();
		this.balance = dto.getBalance();
		this.gender = dto.getGender();
		this.pass = dto.getPassword();
		this.productList = dto.getProductList();
	}

	public LocalDateTime getDateRegister() {
		return dateRegister;
	}

	public List<Product> getProduct_list() {
		return productList;
	}

	public void setDateRegister(LocalDateTime dateRegister) {
		this.dateRegister = dateRegister;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void buy(Double value, Integer quantity) {
		if (!((value * quantity) > this.balance)) {
			this.balance -= (value * quantity);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, email, id, pass);
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
				&& Objects.equals(pass, other.pass);
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", email=" + email + ", pass=" + pass + ", cpf=" + cpf + "]";
	}

	@Override
	public ClientDTO fromConvert() {
		return new ClientDTO(this);
	}

	/* Spring Security Configurations */

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return this.pass;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
