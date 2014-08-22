package telephony.core.service.impl;

import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import telephony.core.dao.TaxDao;
import telephony.core.entity.jpa.Tax;
import telephony.core.service.TaxService;
import telephony.core.service.bean.Session;

/**
 * asd.
 */
public class TaxServiceImpl 
extends AbstractBasicService<Tax> 
implements TaxService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private TaxDao taxDao;

	@Transactional
	@Override
	public long count(Session session) {
		return taxDao.count();
	}

	@Transactional
	@Override
	public void add(Session session, Tax tax) {		
		logger.debug("TaxServiceImpl.addTax starts");
		
		taxDao.saveOrUpdate(tax);
	}

	@Transactional
	@Override
	public Tax findById(Session session, Long id) {
		logger.debug("TaxServiceImpl.findById starts");
		
		return taxDao.findById(id);
	}

	@Transactional
	@Override
	public Collection<Tax> findInDateRange(Session session, Date from, Date to) {
		logger.debug("TaxServiceImpl.findInDateRange starts");
		logger.debug("params : [ from : {}, to : {} ]", from, to);
		
		return taxDao.findInDateRange(from, to);
	}

	@Transactional
	@Override
	public Collection<Tax> update(Session session, Collection<Tax> taxesToUpdate) {
		logger.debug("TaxServiceImpl.update starts");
		logger.debug("params : [ numberOfElements : {} ] ", taxesToUpdate.size());
		
		return null;
	}

	@Transactional
	@Override
	public void remove(Session session, Collection<Tax> taxesToDelete) {
		logger.debug("TaxServiceImpl.delete starts");
		logger.debug("params : [ numberOfElements : {} ] ", taxesToDelete.size());
		
	}

	@Override
	public Tax update(Session session, Tax tax) {
		logger.debug("TaxServiceImpl.update starts");
		logger.debug("params : [ tax : {} ] ", tax);
				
		return taxDao.saveOrUpdate(tax);
	}

	@Transactional
	@Override
	public void remove(Session session, Tax taxToDelete) {
		logger.debug("TaxServiceImpl.delete starts");
		logger.debug("params : [ tax : {} ]", taxToDelete);
		
		taxDao.removeById(taxToDelete.getId());
	}

	@Override
	public Collection<Tax> findByIds(Session session, Collection<Long> ids) {
		logger.debug("TaxServiceImpl.findByIds starts");
		logger.debug("params : [ ids : {} ]", ids);

		return taxDao.findByIds(ids);
	}

}
