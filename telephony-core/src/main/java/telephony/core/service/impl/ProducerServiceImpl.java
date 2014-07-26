package telephony.core.service.impl;

import com.google.inject.persist.Transactional;

import telephony.core.entity.jpa.Producer;
import telephony.core.service.ProducerService;

/**
 * asd.
 */
public class ProducerServiceImpl extends AbstractBasicService<Producer> implements ProducerService {

	@Override
	public Producer findByLabel(String label) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
