package war.server.service;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import war.client.service.DeliveryRPCService;
import war.server.gilead.GuicePersistentRemoteServiceServlet;

@SuppressWarnings("serial")
@Singleton
public class DeliveryRPCServiceImpl extends GuicePersistentRemoteServiceServlet implements DeliveryRPCService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    public DeliveryRPCServiceImpl(PersistentBeanManager beanManager) {
        setBeanManager(beanManager);
    }

}
