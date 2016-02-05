package dev.customer.action;

import dev.customer.data.InitialData;
import dev.customer.entities.Client;
import dev.customer.entities.ClientSession;
/**
 * 
 * @author https://github.com/cagatayes85
 *
 */
public class CustomerActionFactory {

	public static CustomerAction createDefault() {

		CustomerActionImpl CustomerAction = new CustomerActionImpl(
				new ClientSession(), InitialData.GenerateData()); 
		return CustomerAction;
	}

	public static CustomerAction createDefault(Client user) {

		CustomerActionImpl CustomerAction = new CustomerActionImpl(user,
				InitialData.GenerateData() );  
		return CustomerAction;
	}

	public static CustomerAction createDefault(String sessionId) { 
		ClientSession user = new ClientSession();
		user.setSessionId(sessionId);
		CustomerActionImpl CustomerAction = new CustomerActionImpl(user,
				InitialData.GenerateData() );   
		return CustomerAction;
	}

}
