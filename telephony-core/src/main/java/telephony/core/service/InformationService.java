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
