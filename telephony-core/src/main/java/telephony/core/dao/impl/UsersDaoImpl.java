package telephony.core.dao.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.UsersDao;
import telephony.core.entity.jpa.User;

/**
 * asd.
 * 
 * @author Paweł Henek <pawelhenek@gmail.com>
 * 
 */
public class UsersDaoImpl extends GenericDaoImpl<User> implements UsersDao {

	/**
	 * asd.
	 */
	public UsersDaoImpl() {
		super(User.class);
	}

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public User findByName(String name) {

		logger.info("findByName starts ");
		logger.info("params : [ name = {} ]", name);

		User u = (User) getEntityManager()
				.createQuery("select e from User e where e.email = ?1")
				.setParameter(1, name).getSingleResult();

		logger.info("found {} element", u);

		return u;
	}

	@Override
	public User findByNameAndPassword(String name, String password) {
		logger.info("findByNameAndPassword starts");
		logger.info("params : [ name = {} , password = {} ]", name, password);

		// TODO : use encrypted password
		User u = (User) getEntityManager()
				.createQuery(
						"select e from User e where e.email = ?1 and e.password = ?2")
				.setParameter(1, name).setParameter(2, password)
				.getSingleResult();

		logger.info("found {} element", u);

		return u;
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

	@Override
	public User findByNameAndSessionId(String username, String sessionId) {

		logger.info("findByNameAndSessionId starts");

		User u = (User) getEntityManager()
				.createQuery(
						"select e from User e where e.email = ?1 and e.sessionId = ?2")
				.setParameter(1, username).setParameter(2, sessionId)
				.getSingleResult();

		return u;

	}

	@Override
	public List<User> findByStoreId(Long storeId) {

		logger.info("findByStoreId starts");
		logger.info("params : [ storeId : {} ] ", storeId);

		List<User> lst = (List<User>) getEntityManager()
				.createQuery(
						"select e from User e inner join fetch e.allowedShops s where s.id = ?1")
				.setParameter(1, storeId).getResultList();

		logger.info("found {} elements", lst.size());

		return lst;
	}
	
	@Override
	public void remove(User userId) {
		
		logger.info("removeById starts");
		
		this.removeUserStores(userId);
		
		this.removeUserRoles(userId);
		
		super.remove(userId);			
	}

	/**
	 * maybe there will be a need to make it public API.
	 * @param user asd.
 	 */
	private void removeUserRoles(User user) {
		
		getEntityManager()
		.createQuery("delete from UserRole ur where ur.userId = ?1")
		.setParameter(1, user)
		.executeUpdate();
		
		return;
	}

	/**
	 * maybe there will be a need to make it public API..
	 * @param user asd.
	 */
	private void removeUserStores(User user) {
		
		getEntityManager()
		.createQuery("delete from UserStore us where us.userId = ?1")
		.setParameter(1, user)
		.executeUpdate();
		
		return;
	}
}