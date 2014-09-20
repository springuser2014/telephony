package telephony.core.service.impl;

import java.util.Collection;
import java.util.Date;

import telephony.core.dao.PricingsDao;
import telephony.core.entity.jpa.Pricing;
import telephony.core.service.PricingService;
import telephony.core.service.dto.SessionDto;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

/**
 * ad.
 */
public class PricingServiceImpl
extends AbstractBasicService<Pricing> 
implements PricingService {
	
	@Inject
	private PricingsDao pricingsDao;

	@Transactional
	@Override
	public long count(SessionDto session) {
		
		logger.debug("PricingServiceImpl.count starts");

		return pricingsDao.count();
	}

	@Transactional
	@Override
	public Collection<Pricing> findByDateRange(SessionDto session, Date from, Date to) {

		logger.debug("PricingServiceImpl.findByDateRange starts");        
		logger.debug("params : [ from: {}, to : {} ]", from, to);

		return pricingsDao.findByDateRange(from, to);
	}

	@Transactional
	@Override
	public Pricing findById(SessionDto session, long id) {

		logger.debug("PricingServiceImpl.findById starts");        
		logger.debug("params : [ id: {} ]", id);

		return pricingsDao.findById(id);
	}

	@Transactional
	@Override
	public Collection<Pricing> findByIds(SessionDto session, Collection<Long> ids) {

		logger.debug("PricingServiceImpl.findByIds starts");        
		logger.debug("params : [ ids: {} ]", ids.size());

		return pricingsDao.findByIds(ids);
	}

	@Transactional
	@Override
	public Pricing update(SessionDto session, Pricing pricing) {
		
		return pricingsDao.saveOrUpdate(pricing);
	}
	
	@Transactional
	@Override
	public Collection<Pricing> update(SessionDto session, Collection<Pricing> coll) {
		
		return pricingsDao.saveOrUpdate(coll);
	}

	@Transactional
	@Override
	public void remove(SessionDto session, Pricing pricing) {
		
		pricingsDao.remove(pricing);
	}

	@Transactional
	@Override
	public void remove(SessionDto session, Collection<Pricing> pricings) {
		
		pricingsDao.remove(pricings);
	}
	
	@Transactional
	@Override
	public void removeById(SessionDto session, long id) {
		
		pricingsDao.removeById(id);
	}

	@Transactional
	@Override
	public void removeByIds(SessionDto session, Collection<Long> ids) {
		
		pricingsDao.removeByIds(ids);
	}

}
