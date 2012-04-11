package war.server.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import war.client.service.InformationRPCService;
import war.server.core.entity.Store;
import war.server.core.service.interfaces.InformationService;
import war.server.gilead.GuicePersistentRemoteServiceServlet;


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
