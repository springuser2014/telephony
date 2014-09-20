package telephony.core.service;

import telephony.core.service.dto.*;

/**
 * asd.
 */
public interface InformationService {

    /**
     * 
     * @param request
     * @return
     */
    DashboardDetailsResponseDto dashboardDetails(DashboardDetailsRequestDto request);
    
    /**
     * 
     * @param request
     * @return
     */
    ProducersResponseDto fetchProducers(ProducersRequestDto request);
    
    /**
     * 
     * @param request
     * @return
     */
    ModelsResponseDto fetchModels(ModelsRequestDto request);
    
}
