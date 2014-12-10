package telephony.core.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ContactsDao;
import telephony.core.entity.jpa.Contact;
import telephony.core.query.filter.ContactFilterCriteria;

import javax.persistence.Query;

import static telephony.core.assertion.CommonAssertions.isNotNull;

/**
 * Contacts management DAO.
 */
public class ContactsDaoImpl 
extends GenericDaoImpl<Contact> 
implements ContactsDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     */
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
	public List<Contact> find(ContactFilterCriteria filters) {

		boolean whereAdded = false;
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct e from Contact e ");
		queryStr.append(" left outer join e.emails em left outer join e.phonenumbers p left outer join e.faxes f ");

		if (isNotNull(filters.getId())) {
			queryStr.append(" where ");

			whereAdded = true;

			queryStr.append(" e.id = :id ");
		}

		if(isNotNull(filters.getDetails())) {
			if (!whereAdded) {
				queryStr.append(" where ");
				whereAdded = true;
			} else {
				queryStr.append(" and ");
			}

			whereAdded = true;
			queryStr.append(" e.details = :details ");
		}

		if(isNotNull(filters.getLabel())) {
			if (!whereAdded) {
				queryStr.append(" where ");
				whereAdded = true;
			} else {
				queryStr.append(" and ");
			}

			queryStr.append(" e.label = :label ");
		}

		if(isNotNull(filters.getEmail())) {
			if (!whereAdded) {
				queryStr.append(" where ");
				whereAdded = true;
			} else {
				queryStr.append(" and ");
			}

			queryStr.append(" em.content = :email ");
		}

		if (isNotNull(filters.getPhonenumber())) {
			if (!whereAdded) {
				queryStr.append(" where ");
				whereAdded = true;
			} else {
				queryStr.append(" and ");
			}

			queryStr.append(" p.content = :phonenumber ");
		}

		if (isNotNull(filters.getFax())) {
			if (!whereAdded) {
				queryStr.append(" where ");
				whereAdded = true;
			} else {
				queryStr.append(" and ");
			}

			queryStr.append(" f.content = :fax ");
		}

		Query query = getEntityManager()
				.createQuery(queryStr.toString());

		if (isNotNull(filters.getPage()) && isNotNull(filters.getPerPage())) {
			query.setFirstResult((filters.getPerPage() - 1)* filters.getPage());
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

		if (isNotNull(filters.getId())) {
			query.setParameter("id", filters.getId());
		}

		List<Contact> contact = (List<Contact>) query.getResultList();

		return contact;
		
	}
}
