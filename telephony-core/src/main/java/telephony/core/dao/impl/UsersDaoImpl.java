
package telephony.core.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.dao.UsersDao;
import telephony.core.entity.jpa.User;
import telephony.core.query.filter.UserFilterCriteria;
import telephony.core.service.dto.UserChangePasswordDto;

import javax.persistence.Query;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import static telephony.core.assertion.CommonAssertions.isNotEmpty;
import static telephony.core.assertion.CommonAssertions.isNotNull;

public class UsersDaoImpl 
extends GenericDaoImpl<User> 
implements UsersDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());

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
				.createQuery("select e from User e where e.email = ?1 and e.sessionId = ?2")
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
				.createQuery("select e from User e inner join fetch e.allowedShops s where s.id = ?1")
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

	@Override
	public List<User> findByCriteria(UserFilterCriteria filters) {
		logger.info("UsersDaoImpl.findByCriteria starts");

		if(logger.isDebugEnabled()) {
			logger.debug(" params : [ filters : {} ]", filters);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct u from User u left outer join u.allowedShops s ");
		sb.append(" where 1=1 ");

		if (isNotNull(filters.getEmail())) {
			sb.append(" and where u.email = :email ");
		}

		if (isNotNull(filters.getIsActive())) {
			sb.append(" and u.isActive = :isActive ");
		}

		if (isNotNull(filters.getLastLoginFrom())) {
			sb.append(" and u.sessionValidity >= :lastLoginFrom ");
		}

		if (isNotNull(filters.getLastLoginTo())) {
			sb.append(" and u.sessionValidity <= :lastLoginTo ");
		}

		if (isNotEmpty(filters.getUserIds())) {
			sb.append(" and u.id IN (:ids) ");
		}

		if (isNotEmpty(filters.getStoreIds())) {
			sb.append(" and s.id IN (:storeIds) ");
		}

		Query q = getEntityManager().createQuery(sb.toString());

		if (isNotNull(filters.getEmail())) {
			q.setParameter("email", filters.getEmail());
		}

		if (isNotNull(filters.getIsActive())) {
			q.setParameter("isActive", filters.getIsActive());
		}

		if (isNotNull(filters.getLastLoginFrom())) {
			q.setParameter("lastLoginFrom", filters.getLastLoginFrom());
		}

		if (isNotNull(filters.getLastLoginTo())) {
			q.setParameter("lastLoginTo", filters.getLastLoginTo());
		}

		if (isNotEmpty(filters.getUserIds())) {
			q.setParameter("ids", filters.getUserIds());
		}

		if (isNotEmpty(filters.getStoreIds())) {
			q.setParameter("storeIds", filters.getStoreIds());
		}
		// TODO extract to common
		if (isNotNull(filters.getPage()) && isNotNull(filters.getPerPage())) {
			q.setFirstResult((filters.getPerPage())* filters.getPage());
			q.setMaxResults(filters.getPerPage());
		}

		if (isNotNull(filters.getPerPage())) {
			q.setMaxResults(filters.getPerPage());
		}

		List<User> users = (List<User>) q.getResultList();

		return users;
	}

	@Override
	public Long countByCriteria(UserFilterCriteria filters) {

		logger.info("UsersDaoImpl.countByCriteria starts");

		if(logger.isDebugEnabled()) {
			logger.debug(" params : [ filters : {} ]", filters);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(" select count(distinct u) from User u left outer join u.allowedShops s ");
		sb.append(" where 1=1 ");

		if (isNotNull(filters.getEmail())) {
			sb.append(" and where u.email = :email ");
		}

		if (isNotNull(filters.getIsActive())) {
			sb.append(" and u.isActive = :isActive ");
		}

		if (isNotNull(filters.getLastLoginFrom())) {
			sb.append(" and u.sessionValidity >= :lastLoginFrom ");
		}

		if (isNotNull(filters.getLastLoginTo())) {
			sb.append(" and u.sessionValidity <= :lastLoginTo ");
		}

		if (isNotEmpty(filters.getUserIds())) {
			sb.append(" and u.id IN (:ids) ");
		}

		if (isNotEmpty(filters.getStoreIds())) {
			sb.append(" and s.id IN (:storeIds) ");
		}

		Query q = getEntityManager()
				.createQuery(sb.toString());

		if (isNotNull(filters.getEmail())) {
			q.setParameter("email", filters.getEmail());
		}

		if (isNotNull(filters.getIsActive())) {
			q.setParameter("isActive", filters.getIsActive());
		}

		if (isNotNull(filters.getLastLoginFrom())) {
			q.setParameter("lastLoginFrom", filters.getLastLoginFrom());
		}

		if (isNotNull(filters.getLastLoginTo())) {
			q.setParameter("lastLoginTo", filters.getLastLoginTo());
		}

		if (isNotEmpty(filters.getUserIds())) {
			q.setParameter("ids", filters.getUserIds());
		}

		if (isNotEmpty(filters.getStoreIds())) {
			q.setParameter("storeIds", filters.getStoreIds());
		}
		// TODO extract to common
		if (isNotNull(filters.getPage()) && isNotNull(filters.getPerPage())) {
			q.setFirstResult((filters.getPerPage())* filters.getPage());
			q.setMaxResults(filters.getPerPage());
		}

		if (isNotNull(filters.getPerPage())) {
			q.setMaxResults(filters.getPerPage());
		}

		Long count = (Long) q.getSingleResult();

		return count;
	}

	@Override
	public void changePassword(UserChangePasswordDto userDto) throws NoSuchAlgorithmException {

		logger.info("UsersDaoImpl.changePassword starts");
		User user = findById(userDto.getUserId());

		user.setPassword(encodePassword(userDto.getPassword1()));

		this.saveOrUpdate(user);
	}

	@Override
	public String encodePassword(String bytes) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(bytes.getBytes());
		byte[] digest = md.digest();

		return new String (digest);
	}
}




