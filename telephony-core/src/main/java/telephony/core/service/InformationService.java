package telephony.core.service;

import telephony.core.service.dto.request.DashboardDetailsRequest;
import telephony.core.service.dto.request.ModelsRequest;
import telephony.core.service.dto.response.DashboardDetailsResponse;
import telephony.core.service.dto.response.ModelsResponse;
import telephony.core.service.dto.request.ProducersRequest;
import telephony.core.service.dto.response.ProducersResponse;

/**
 * asd.
 */
public interface InformationService {


    /**
     * 
     * @param request
     * @return
     */
    DashboardDetailsResponse dashboardDetails(DashboardDetailsRequest request);
    
    /**
     * 
     * @param request
     * @return
     */
    ProducersResponse fetchProducers(ProducersRequest request);
    
    /**
     * 
     * @param request
     * @return
     */
    ModelsResponse fetchModels(ModelsRequest request);
    
}
