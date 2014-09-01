package telephony.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.DeliveriesDao;
import telephony.core.dao.SalesDao;
import telephony.core.service.InformationService;
import telephony.core.service.dto.*;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;


/**
 * Information management service.
 */
public class InformationServiceImpl implements InformationService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private DeliveriesDao deliveriesDao;

    @Inject
    private SalesDao salesDao;
    
	@Override
	@Transactional
	public DashboardDetailsResponse dashboardDetails(DashboardDetailsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ProducersResponse fetchProducers(ProducersRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ModelsResponse fetchModels(ModelsRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
