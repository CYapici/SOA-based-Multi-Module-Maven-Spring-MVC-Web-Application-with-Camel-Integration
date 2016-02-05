package dev.customer.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author https://github.com/cagatayes85
 *
 */
@Entity
@Table(name = "Bank")
@XmlRootElement
public class Bank extends Corporation {

	private static final long serialVersionUID = -4957779215334440965L;

	private String swift;

	private String iban;

	public String getIban() {
		return iban;
	}

	public Bank(String iban_, String swift_) {
		iban = iban_;
		swift = swift_;

	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public Bank() {
		super();
	}

	public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

}