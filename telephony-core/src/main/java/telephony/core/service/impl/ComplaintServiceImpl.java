package telephony.core.service.impl;

import com.google.inject.persist.Transactional;

import telephony.core.entity.jpa.Complaint;
import telephony.core.service.ComplaintService;

/**
 * asd.
 */
public class ComplaintServiceImpl 
extends AbstractBasicService<Complaint> implements ComplaintService {

	@Transactional
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
