package dev.customer.persistence;

/**
 * ACTIONS AND MEMBER MANIPULATION INTERFACE
 * @author https://github.com/cagatayes85
 */

import dev.customer.action.CustomerAction;
import dev.customer.entities.Corporation;

public interface CorporationDao {
	
	// BANK ACCOUNTS

	public void DeleteCorporation(String sessionId, Long corporationID);

	public Corporation InsertCorporation(String sessionId,
			String iban, String swift);

	// ACTIONS
	public CustomerAction InsertAction(CustomerAction action);

	public CustomerAction Init(String sessionId);

	public CustomerAction GetActionBySessionID(String sessionId);

}
