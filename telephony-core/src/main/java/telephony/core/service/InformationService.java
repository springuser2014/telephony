package telephony.core.service;

import telephony.core.service.dto.request.DashboardDetailsRequest;
import telephony.core.service.dto.request.ModelsRequest;
import telephony.core.service.dto.response.DashboardDetailsResponse;
import telephony.core.service.dto.response.ModelsResponse;
import telephony.core.service.dto.request.ProducersRequest;
import telephony.core.service.dto.response.ProducersResponse;

public interface InformationService {

    DashboardDetailsResponse dashboardDetails(DashboardDetailsRequest request);
    
    ProducersResponse fetchProducers(ProducersRequest request);
    
    ModelsResponse fetchModels(ModelsRequest request);
    
}
