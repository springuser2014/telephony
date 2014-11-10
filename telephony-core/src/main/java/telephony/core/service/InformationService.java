package telephony.core.service;

import telephony.core.service.dto.*;
import telephony.core.service.dto.request.DashboardDetailsRequestDto;
import telephony.core.service.dto.request.ModelsRequestDto;
import telephony.core.service.dto.request.ProducersRequestDto;
import telephony.core.service.dto.response.DashboardDetailsResponseDto;
import telephony.core.service.dto.response.ModelsResponseDto;
import telephony.core.service.dto.response.ProducersResponseDto;

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
