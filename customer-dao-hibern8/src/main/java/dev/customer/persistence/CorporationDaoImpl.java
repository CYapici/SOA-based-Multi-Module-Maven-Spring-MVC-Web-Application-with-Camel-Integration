package dev.customer.persistence;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.customer.action.CustomerAction;
import dev.customer.action.CustomerActionFactory;
import dev.customer.entities.Bank;
import dev.customer.entities.Corporation;

/**
 * DATA ACCESS LAYER / PERSISTANCE LAYER
 * 
 * @author https://github.com/cagatayes85
 *
 */
@Service
public class CorporationDaoImpl implements CorporationDao {

	private transient static final Logger logger = LoggerFactory
			.getLogger(CorporationDaoImpl.class);
	protected final static String CLIENTBYSESSIONID = "from dev.customer.entities.Client where sessionId=:sessionId";

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public CustomerAction Init(String sessionId) {

		Session session = sessionFactory.openSession();
		Query user = session.createQuery(CLIENTBYSESSIONID).setString(
				"sessionId", sessionId);
		List<?> list = user.list();
		session.close();

		if (list.isEmpty()) {
			InsertAction(CustomerActionFactory.createDefault(sessionId));
			logger.info("INITIAL DATA HAS BEEN INSERTED ELEMENTS SIZE :"
					+ list.size());
		}

		return GetActionBySessionID(sessionId);
	}

	@Transactional
	public CustomerAction InsertAction(CustomerAction action) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		session.saveOrUpdate(action);
		tx.commit();
		session.close();
		logger.info("INSERTED action with CUSTOMER ACTION id: "
				+ action.getCustomerActionID());
		return action;
	}

	public CustomerAction GetActionBySessionID(
			String sessionId) {

		Session session = sessionFactory.openSession();
		String queryStr = "from dev.customer.action.CustomerAction where sessionId=:sessionId";
		Query user = session.createQuery(queryStr).setString(
				"sessionId", sessionId);
		CustomerAction action = (CustomerAction) user.uniqueResult();
		session.close();
		logger.info("GOT action with session id: " + sessionId);
		return action;
	}

	/**
	 * BELOW ARE CORPORATION
	 * CRUD////////////////////////////////////////////////////////
	 */

	@Transactional
	public void DeleteCorporation(String sessionId, Long corporationID) {

		Session session = sessionFactory.openSession();
		String queryStr = "from dev.customer.action.CustomerAction where sessionId=:sessionId";
		Query user = session.createQuery(queryStr).setString(
				"sessionId", sessionId);
		CustomerAction action = (CustomerAction) user.uniqueResult();

		for (Corporation corporation : action.getAcounts()) {
			if (corporation.getcorporationID().longValue() == corporationID
					.longValue()) {

				action.getAcounts().remove(corporation);

				break;
			}
		}
		session.saveOrUpdate(action);
		session.flush();
		session.close();

		logger.info("DELETED corporation with id: " + corporationID);
	}

	@Transactional
	public void EditCorporation(Long corporationID, String iban, String swift) {

		Session session = sessionFactory.openSession();
		Corporation foundation = (Corporation) session.get(
				Corporation.class, corporationID);

		foundation.setSwift(swift);
		foundation.setIban(iban);

		session.saveOrUpdate(foundation);
		session.flush();
		session.close();
		logger.info("EDITED corporation with id: " + corporationID);
	}

	@Transactional
	public Corporation InsertCorporation(String sessionId, String iban,
			String swift) {

		CustomerAction customerAction = GetActionBySessionID(sessionId);

		Set<Corporation> accounts = customerAction.getAcounts();

		for (Corporation ff : accounts) {
			if (ff.getIban().equals(iban) && ff.getSwift().equals(swift))
				try {
					throw new Exception("iban / swift::: " + iban + "/" + swift);
				} catch (Exception e) {
					e.printStackTrace();
				}

		}

		Bank bank = new Bank(iban, swift);

		customerAction.getAcounts().add(bank);
		InsertAction(customerAction);
		return bank;
	}

}
