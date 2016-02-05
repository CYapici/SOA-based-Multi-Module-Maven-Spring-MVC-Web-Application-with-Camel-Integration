package dev.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.customer.action.CustomerAction;
import dev.customer.entities.Corporation;
import dev.customer.persistence.CorporationDao;

/**
 * 
 * @author https://github.com/cagatayes85
 *
 */
@Service
public class CustomerActionServiceImpl implements CustomerActionService {

	@Autowired
	private CorporationDao dao;

	@Override
	public CustomerAction SaveAction(CustomerAction Action) {
		return dao.InsertAction(Action);
	}

	@Override
	public CustomerAction Init(String sessionId) {
		return dao.Init(sessionId);
	}
 
	 

	@Override
	public Corporation SaveCorporation(String sessionId,
			String iban, String swift) {
		return dao.InsertCorporation(sessionId, iban, swift);
	}

	@Override
	public CustomerAction GetActionBySessionID(String sessionId) {
		return dao.GetActionBySessionID(sessionId);
		 
	}

	@Override
	public void DeleteCorporation(String sessionId, Long corporationID) {
		dao.DeleteCorporation(sessionId, corporationID);
		
	}

}
