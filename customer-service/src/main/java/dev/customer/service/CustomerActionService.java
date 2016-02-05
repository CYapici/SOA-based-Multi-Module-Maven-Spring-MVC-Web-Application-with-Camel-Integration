package dev.customer.service;

/**
 * 
 * @author https://github.com/cagatayes85
 *
 */
import dev.customer.action.CustomerAction;
import dev.customer.entities.Corporation;

public interface CustomerActionService {

	public CustomerAction SaveAction(CustomerAction Action);

	public CustomerAction Init(String sessionId);

	public CustomerAction GetActionBySessionID(String sessionId);

	public void DeleteCorporation(String sessionId, Long corporationID);

	public Corporation SaveCorporation(String sessionId,
			String iban, String swift);

}
