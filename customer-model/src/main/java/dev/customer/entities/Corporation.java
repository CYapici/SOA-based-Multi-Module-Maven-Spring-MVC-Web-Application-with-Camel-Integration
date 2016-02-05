package dev.customer.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@XmlRootElement
public abstract class Corporation implements Serializable {

	private static final long serialVersionUID = -1776836331847094485L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "corporationID")
	protected Long corporationID;

	public Long getcorporationID() {
		return corporationID;
	}

	public void setcorporationID(Long corporationID) {
		this.corporationID = corporationID;
	}

 
	public abstract void setIban(String iban);

	public abstract String getIban();

	public abstract void setSwift(String swift);

	public abstract String getSwift();

}
