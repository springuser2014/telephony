package war.server.guice;


import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import net.sf.gilead.core.hibernate.jpa.HibernateJpaUtil;
import net.sf.gilead.gwt.GwtConfigurationHelper;
import net.sf.gilead.gwt.PersistentRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@Singleton
public class GuiceRemoteServiceServlet extends PersistentRemoteService {

    private static Logger logger = LoggerFactory.getLogger(GuiceRemoteServiceServlet.class);

    @Inject
    private Injector injector;

    @Inject
    private EntityManager em;

    @Override
    public String processCall(String payload) throws SerializationException {

        try {
            RPCRequest req = RPC.decodeRequest(payload, null, this);

            RemoteService service = getServiceInstance(
                    req.getMethod().getDeclaringClass());

            return RPC.invokeAndEncodeResponse(service, req.getMethod(),
                    req.getParameters(), req.getSerializationPolicy());
        } catch (IncompatibleRemoteServiceException ex) {
            log("IncompatibleRemoteServiceException in the processCall(String) method.",
                    ex);
            return RPC.encodeResponseForFailure(null, ex);
        }
    }

    @SuppressWarnings({"unchecked"})
    private RemoteService getServiceInstance(Class serviceClass) {
        return (RemoteService) injector.getInstance(serviceClass);
    }


    public GuiceRemoteServiceServlet() {

        logger.debug("GuiceRemoteServiceServlet constructor starts");
        logger.debug("Entity manager : {} ", em);

        HibernateJpaUtil hibernateJpaUtil = new HibernateJpaUtil(em.getEntityManagerFactory());

        PersistentBeanManager persistentBeanManager =
                GwtConfigurationHelper.initGwtStatelessBeanManager(hibernateJpaUtil);

        this.setBeanManager(persistentBeanManager);

        logger.debug("GuiceRemoteServiceServlet constructor ends");
    }

}