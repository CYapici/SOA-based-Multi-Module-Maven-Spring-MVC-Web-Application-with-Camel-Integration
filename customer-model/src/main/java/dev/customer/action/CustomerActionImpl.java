package dev.customer.action;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import dev.customer.entities.Client;
import dev.customer.entities.Corporation;

/**
 * 
 * @author https://github.com/cagatayes85
 *
 */
@Entity
@Table(name = "customerActionCatalog")
@XmlRootElement
public class CustomerActionImpl implements CustomerAction {

	private static final long serialVersionUID = -8362372248560754244L;

	public CustomerActionImpl(Client clientSession, Set<Corporation> data) {
		user = clientSession;
		acounts = data;
	}

	// needed for Hibernate
	public CustomerActionImpl() {
	}

	@Id
	@GeneratedValue
	@Column(name = "customerActionCatalogId")
	private Long customerActionCatalogId;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderBy("corporationID")
	@JoinTable(name = "CustomerAccounts", joinColumns = { @JoinColumn(name = "customerActionCatalogId") }, inverseJoinColumns = { @JoinColumn(name = "corporationID") })
	private Set<Corporation> acounts;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "sessionId")
	private Client user;

	public Long getCustomerActionID() {
		return customerActionCatalogId;
	}

	public void setCustomerAction(Long id) {
		this.customerActionCatalogId = id;
	}

	public Client getUser() {
		return user;
	}

	public void setUser(Client user_) {
		this.user = user_;
	}

	public Set<Corporation> getAcounts() {
		return acounts;
	}

	public void setAcounts(Set<Corporation> acounts) {
		this.acounts = acounts;
	}

}
