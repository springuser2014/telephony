package telephony.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.DeliveriesDao;
import telephony.core.dao.SalesDao;
import telephony.core.service.InformationService;
import telephony.core.service.dto.*;
import telephony.core.service.dto.request.DashboardDetailsRequestDto;
import telephony.core.service.dto.request.ModelsRequestDto;
import telephony.core.service.dto.request.ProducersRequestDto;
import telephony.core.service.dto.response.DashboardDetailsResponseDto;
import telephony.core.service.dto.response.ModelsResponseDto;
import telephony.core.service.dto.response.ProducersResponseDto;

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
	public DashboardDetailsResponseDto dashboardDetails(DashboardDetailsRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ProducersResponseDto fetchProducers(ProducersRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ModelsResponseDto fetchModels(ModelsRequestDto request) {
		// TODO Auto-generated method stub
		return null;
	}

}
