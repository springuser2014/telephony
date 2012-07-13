package telephony.server.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import telephony.client.service.InformationRPCService;
import telephony.server.core.entity.Store;
import telephony.server.core.service.interfaces.InformationService;
import telephony.server.gilead.GuicePersistentRemoteServiceServlet;


@SuppressWarnings("serial")
@Singleton
public class InformationRPCServiceImpl extends GuicePersistentRemoteServiceServlet implements InformationRPCService {

    @Inject
    private InformationService informationService;

    @Inject
    public InformationRPCServiceImpl(PersistentBeanManager beanManager) {
        setBeanManager(beanManager);
    }
    
    public Long getNumberOfDeliveries(Store store) {
        return informationService.getNumberOfDeliveries(store);
    }

    public Long getNumberOfSales(Store store) {
        return informationService.getNumberOfSales(store);
    }
}
