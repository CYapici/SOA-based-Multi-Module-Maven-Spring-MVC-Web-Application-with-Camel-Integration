package dev.customer.pooling;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import dev.customer.action.CustomerAction;
import dev.customer.action.CustomerActionFactory;
import dev.customer.entities.Bank;
import dev.customer.entities.ClientSession;
import dev.customer.entities.Corporation;
import dev.customer.persistence.CorporationDao;

/**
 * thread safe way of cached seperate session for each user
 * 
 * @author https://github.com/cagatayes85
 *
 */
@Service
@Scope(value = "singleton")
public class Actionpool implements CorporationDao {

	private transient static final Logger logger = LoggerFactory
			.getLogger(Actionpool.class);

	private Map<String, CustomerAction> actionCache = new HashMap<>();

	private AtomicLong id = new AtomicLong(0L);

	@Override
	public synchronized CustomerAction InsertAction(
			CustomerAction action) {

		actionCache.put(action.getUser().getSessionId(), action);
		return action;
	}

	@Override
	public synchronized CustomerAction Init(String sessionId) {

		logger.info("initiallizing with sessionId: " + sessionId);

		if (actionCache.containsKey(sessionId)) {
			return actionCache.get(sessionId);
		}
		// get action
		CustomerAction createDefault = CustomerActionFactory.createDefault();

		createDefault.setCustomerAction(id.incrementAndGet());

		// get session
		ClientSession userSession = new ClientSession();

		userSession.setSessionId(sessionId);

		createDefault.setUser(userSession);
		//get all accounts exist and assign them IDS
		Set<Corporation> acounts = createDefault.getAcounts();
		
		Corporation item;
		for (Iterator<Corporation> iterator = acounts.iterator(); iterator
				.hasNext();) {
			item = iterator.next();
			item.setcorporationID(id.incrementAndGet());
		}
		item=null; 
		
		actionCache.put(sessionId, createDefault);

		return createDefault;
	}

	@Override
	public synchronized CustomerAction GetActionBySessionID(
			String sessionId) {
		return actionCache.get(sessionId);
	}

	@Override
	public synchronized void DeleteCorporation(String sessionId,
			Long corporationID) {

		CustomerAction customerAct = actionCache.get(sessionId);
		Set<Corporation> accounts = customerAct.getAcounts();

		// find and remove
		for (Iterator<Corporation> iterator = accounts.iterator(); iterator
				.hasNext();) {
			Corporation item = iterator.next();
			if (item.getcorporationID().equals(corporationID)) {
				accounts.remove(item);
				break;
			}
		}

	}

	@Override
	public synchronized Corporation InsertCorporation(String sessionId,
			String iban, String swift) {

		Corporation item = new Bank();
		item.setSwift(swift);
		item.setIban(iban);
		item.setcorporationID(id.incrementAndGet());

		actionCache.get(sessionId).getAcounts().add(item);

		return item;
	}

}
