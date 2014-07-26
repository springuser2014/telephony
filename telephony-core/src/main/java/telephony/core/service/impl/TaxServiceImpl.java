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

/**
 * asd.
 */
public class TaxServiceImpl extends AbstractBasicService<Tax> implements TaxService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private TaxDao taxDao;

	@Transactional
	@Override
	public long count() {
		return taxDao.count();
	}

	@Transactional
	@Override
	public void addTax(Tax tax) {		
		logger.debug("TaxServiceImpl.addTax starts");
		
		taxDao.saveOrUpdate(tax);
	}

	@Transactional
	@Override
	public Tax findById(Long id) {
		logger.debug("TaxServiceImpl.findById starts");
		
		return taxDao.findById(id);
	}

	@Transactional
	@Override
	public Collection<Tax> findInDateRange(Date from, Date to) {
		logger.debug("TaxServiceImpl.findInDateRange starts");
		logger.debug("params : [ from : {}, to : {} ]", from, to);
		
		return taxDao.findInDateRange(from, to);
	}

	@Transactional
	@Override
	public Collection<Tax> update(Collection<Tax> taxesToUpdate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void delete(Collection<Tax> taxesToDelete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tax update(Tax tax) {
		logger.debug("TaxServiceImpl.update starts");
		
		return taxDao.saveOrUpdate(tax);
	}

	@Transactional
	@Override
	public void delete(Tax taxToDelete) {
		logger.debug("TaxServiceImpl.delete starts");
		logger.debug("params : [ taxId : {} ]", taxToDelete.getId());
		
		taxDao.removeById(taxToDelete.getId());
	}

}
