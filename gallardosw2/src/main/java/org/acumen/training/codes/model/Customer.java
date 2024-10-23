package org.acumen.training.codes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(catalog = "bestbuy", name = "customer")
public class Customer {
	private Integer customer_id;
	private String cust_name;
	private String city;
	private Integer grade;
	private Integer salesman_id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "customer_id" , nullable =  false, unique = true)
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	
	@Column(name = "cust_name", nullable = false)
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	@Column(name = "city", nullable = false)
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name = "grade")
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	@Column(name = "salesman_id")
	public Integer getSalesman_id() {
		return salesman_id;
	}
	public void setSalesman_id(Integer salesman_id) {
		this.salesman_id = salesman_id;
	}
	@Override
	public String toString() {
		return String.format("Customer [customer_id=%s, cust_name=%s, city=%s, grade=%s, salesman_id=%s]", customer_id,
				cust_name, city, grade, salesman_id);
	}
    
}
