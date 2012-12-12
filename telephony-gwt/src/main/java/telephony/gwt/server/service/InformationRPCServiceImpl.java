package telephony.gwt.server.service;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import telephony.gwt.client.service.InformationRPCService;
import telephony.core.entity.Store;
import telephony.core.service.interfaces.InformationService;
import telephony.gwt.server.gilead.GuicePersistentRemoteServiceServlet;


/**
 * @todo Remove after migration to REST services
 */
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
