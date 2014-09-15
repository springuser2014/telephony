
package telephony.core.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.UsersDao;
import telephony.core.entity.jpa.User;
import telephony.core.query.filter.UserFilterCriteria;

/**
 * Users management DAO.
 */
public class UsersDaoImpl 
extends GenericDaoImpl<User> 
implements UsersDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * Sets entities class.
	 */
	public UsersDaoImpl() {
		super(User.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findByName(String name) {

		logger.info("findByName starts ");
		logger.info("params : [ name = {} ]", name);
				
		List<User> lst = (List<User>) getEntityManager()
				.createQuery(
						"select e from User e LEFT JOIN FETCH e.roles " +
						"LEFT JOIN FETCH e.allowedShops where e.email = ?1")
				.setParameter(1, name)
				.getResultList();
		
		if (lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findByNameAndPassword(String name, String password) {
		logger.info("findByNameAndPassword starts");
		logger.info("params : [ name = {} , password = {} ]", name, password);

		// TODO : use encrypted password
		List<User> lst = (List<User>) getEntityManager()
				.createQuery(
						"select e from User e where e.email = ?1 and e.password = ?2")
				.setParameter(1, name).setParameter(2, password)
				.getResultList();

		if (lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public boolean updateSession(Long userId, String sessionId,
			Date sessionValidaty) {
		logger.info("updateSession starts");
		logger.info(
				"params : [ userId = {} , sessionId = {}, sessionValidaty]",
				userId, sessionId, sessionValidaty);

		int i = getEntityManager()
				.createQuery(
						"update User e set e.sessionId = '?1', "
								+ "e.sessionValidity = '?2' where e.id = ?3 ")
				.setParameter(1, sessionId).setParameter(2, sessionValidaty)
				.setParameter(3, userId).executeUpdate();

		logger.info("found {} element", i);

		return i > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findByNameAndSessionId(String username, String sessionId) {

		logger.debug("findByNameAndSessionId starts");
		logger.debug(
				"params : [ username = {} , sessionId = {}]",
				username, sessionId);
		
		List<User> lst = (List<User>) getEntityManager()
				.createQuery(
						"select e from User e where e.email = ?1 and e.sessionId = ?2")
				.setParameter(1, username)
				.setParameter(2, sessionId)
				.getResultList();

		if (lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByStoreId(Long storeId) {

		logger.info("findByStoreId starts");
		logger.info("params : [ storeId : {} ] ", storeId);

		List<User> lst = (List<User>) getEntityManager()
				.createQuery(
						"select e from User e inner join fetch e.allowedShops s where s.id = ?1")
				.setParameter(1, storeId)
				.getResultList();

		logger.info("found {} elements", lst.size());

		return lst;
	}
	
	@Override
	public void remove(User userId) {
		
		logger.info("removeById starts");
		
		super.remove(userId);			
	}

	// TODO : delete unused method.
	/*
	 * maybe there will be a need to make it public API.
 	 */
	private void removeUserRoles(User user) {
		
		getEntityManager()
			.createQuery("delete from UserRole ur where ur.user = ?1")
			.setParameter(1, user)
			.executeUpdate();
		
		return;
	}

	// TODO : delete unused method.
	/*
	 * maybe there will be a need to make it public API..
	 */
	private void removeUserStores(User user) {
		
		getEntityManager()
			.createQuery("delete from UserStore us where us.user = ?1")
			.setParameter(1, user)
			.executeUpdate();
		
		return;
	}

	@Override
	public List<User> find(UserFilterCriteria filters) {
		
		return new ArrayList<User>();
	}
}