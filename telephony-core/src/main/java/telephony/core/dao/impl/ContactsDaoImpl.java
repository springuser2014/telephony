package telephony.core.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ContactsDao;
import telephony.core.entity.jpa.Contact;
import telephony.core.query.filter.ContactFilterCriteria;

import javax.persistence.Query;

import static telephony.core.assertion.CommonAssertions.isNotEmpty;
import static telephony.core.assertion.CommonAssertions.isNotNull;

public class ContactsDaoImpl
extends GenericDaoImpl<Contact> 
implements ContactsDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ContactsDaoImpl() {
        super(Contact.class);
    }

	@Override
	public Contact findByLabel(String label) {

		if (logger.isDebugEnabled()) {
			logger.debug("findByLabel starts ");
			logger.debug("params : [ label = {} ]", label);
		}

		Contact contact = (Contact) getEntityManager()
				.createQuery("select e from Contact e where e.label = ?1")
				.setParameter(1, label)
				.getSingleResult();

		logger.info("found {} element", contact);
		return contact;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> findByCriteria(ContactFilterCriteria filters) {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct e from Contact e ");
		queryStr.append(" left outer join e.emails em left outer join e.phonenumbers p left outer join e.faxes f ");
		queryStr.append(" where 1=1 ");

		if (isNotEmpty(filters.getIds())) {
			queryStr.append(" and e.id IN (:ids) ");
		}

		if(isNotNull(filters.getDetails())) {
			queryStr.append(" and e.details = :details ");
		}

		if(isNotNull(filters.getLabel())) {
			queryStr.append(" and e.label = :label ");
		}

		if(isNotNull(filters.getEmail())) {
			queryStr.append(" and em.content = :email ");
		}

		if (isNotNull(filters.getPhonenumber())) {
			queryStr.append(" and p.content = :phonenumber ");
		}

		if (isNotNull(filters.getFax())) {
			queryStr.append(" and f.content = :fax ");
		}

		if (isNotEmpty(filters.getIgnoreIds())) {
			queryStr.append(" and e.id not in (:ignoreIds) ");
		}

		Query query = getEntityManager()
				.createQuery(queryStr.toString());

		if (isNotNull(filters.getPage()) && isNotNull(filters.getPerPage())) {
			query.setFirstResult((filters.getPerPage())* filters.getPage());
			query.setMaxResults(filters.getPerPage());
		}

		if (isNotNull(filters.getPerPage())) {
			query.setMaxResults(filters.getPerPage());
		}

		if (isNotNull(filters.getDetails())) {
			query.setParameter("details", filters.getDetails());
		}

		if (isNotNull(filters.getLabel())) {
			query.setParameter("label", filters.getLabel());
		}

		if (isNotNull(filters.getFax())) {
			query.setParameter("fax", filters.getFax());
		}

		if (isNotNull(filters.getPhonenumber())) {
			query.setParameter("phonenumber", filters.getPhonenumber());
		}

		if (isNotNull(filters.getEmail())) {
			query.setParameter("email", filters.getEmail());
		}

		if (isNotEmpty(filters.getIds())) {
			query.setParameter("ids", filters.getIds());
		}

		if (isNotEmpty(filters.getIgnoreIds())) {
			query.setParameter("ignoreIds", filters.getIgnoreIds());
		}

		List<Contact> contact = (List<Contact>) query.getResultList();

		return contact;
	}

	@Override
	public Long countByCriteria(ContactFilterCriteria filters) {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select count(distinct e) from Contact e ");
		queryStr.append(" left outer join e.emails em left outer join e.phonenumbers p left outer join e.faxes f ");
		queryStr.append(" where 1=1 ");

		if (isNotEmpty(filters.getIds())) {
			queryStr.append(" and e.id IN (:ids) ");
		}

		if(isNotNull(filters.getDetails())) {
			queryStr.append(" and e.details = :details ");
		}

		if(isNotNull(filters.getLabel())) {
			queryStr.append(" and e.label = :label ");
		}

		if(isNotNull(filters.getEmail())) {
			queryStr.append(" and em.content = :email ");
		}

		if (isNotNull(filters.getPhonenumber())) {
			queryStr.append(" and p.content = :phonenumber ");
		}

		if (isNotNull(filters.getFax())) {
			queryStr.append(" and f.content = :fax ");
		}

		if (isNotEmpty(filters.getIgnoreIds())) {
			queryStr.append(" and e.id not in (:ignoreIds) ");
		}

		Query query = getEntityManager()
				.createQuery(queryStr.toString());

		if (isNotNull(filters.getPage()) && isNotNull(filters.getPerPage())) {
			query.setFirstResult((filters.getPerPage())* filters.getPage());
			query.setMaxResults(filters.getPerPage());
		}

		if (isNotNull(filters.getPerPage())) {
			query.setMaxResults(filters.getPerPage());
		}

		if (isNotNull(filters.getDetails())) {
			query.setParameter("details", filters.getDetails());
		}

		if (isNotNull(filters.getLabel())) {
			query.setParameter("label", filters.getLabel());
		}

		if (isNotNull(filters.getFax())) {
			query.setParameter("fax", filters.getFax());
		}

		if (isNotNull(filters.getPhonenumber())) {
			query.setParameter("phonenumber", filters.getPhonenumber());
		}

		if (isNotNull(filters.getEmail())) {
			query.setParameter("email", filters.getEmail());
		}

		if (isNotEmpty(filters.getIds())) {
			query.setParameter("ids", filters.getIds());
		}

		if (isNotEmpty(filters.getIgnoreIds())) {
			query.setParameter("ignoreIds", filters.getIgnoreIds());
		}

		// TODO improve it later
		List<Long> lst = (List<Long>) query.getResultList();
		long count = 0;

		for (Long l : lst) {
			count += l;
		}

		return count;
	}
}
