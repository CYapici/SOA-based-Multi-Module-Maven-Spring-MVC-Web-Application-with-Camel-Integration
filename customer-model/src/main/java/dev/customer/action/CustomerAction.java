package dev.customer.action;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.MappedSuperclass;

import dev.customer.entities.Client;
import dev.customer.entities.Corporation;

/**
 * the base action class 
 * 
 * @author https://github.com/cagatayes85
 *
 */
@MappedSuperclass
public  interface CustomerAction extends Serializable { 
	


	public   void setCustomerAction(Long id);

	public   Long getCustomerActionID();

	public   void setUser(Client user);

	public   Client getUser();

	public   Set<Corporation> getAcounts();

	public   void setAcounts(Set<Corporation> acounts);
	
	
}
