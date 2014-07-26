package telephony.core.service.impl;

import com.google.inject.persist.Transactional;

import telephony.core.entity.jpa.SaleComplaint;
import telephony.core.service.SaleComplaintService;

/**
 * asd.
 */
public class SaleComplaintServiceImpl 
extends AbstractBasicService<SaleComplaint> implements SaleComplaintService {

	@Transactional
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
