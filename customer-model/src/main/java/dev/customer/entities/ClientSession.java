package dev.customer.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
/**
 * 
 * @author https://github.com/cagatayes85
 *
 */
@Entity
@Table(name = "UserSession")
public class ClientSession extends Client {

	private static final long serialVersionUID = 6903908229583385849L;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
}
