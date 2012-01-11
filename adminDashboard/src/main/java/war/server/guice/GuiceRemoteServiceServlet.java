package war.server.guice;


import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import javax.inject.Inject;

@Singleton
public class GuiceRemoteServiceServlet extends RemoteServiceServlet {

//@Singleton
//public class GuiceRemoteServiceServlet extends PersistentRemoteService {

//    private static Log log = LogFactory.getLog(GuiceRemoteServiceServlet.class);

    @Inject
    private Injector injector;

//    private Provider<PersistentBeanManager> beanManager;
//
//    @Inject
//    public GuiceRemoteServiceServlet(Provider<PersistentBeanManager> provider) {
//        this.beanManager = provider;
//
//        setBeanManager(this.beanManager.get());
//    }

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

//    public GuiceRemoteServiceServlet() {
//        log.debug("GuiceRemoteServiceServlet constructor starts!!");
//        log.info("GuiceRemoteServiceServlet constructor starts!!");
//
//        EntityManagerFactory emf =
//                Persistence.createEntityManagerFactory(Constant.PERSISTENCE_UNIT_NAME);
//        HibernateJpaUtil hibernateJpaUtil = new HibernateJpaUtil(emf);
//
//        PersistentBeanManager persistentBeanManager =
//                GwtConfigurationHelper.initGwtStatelessBeanManager(hibernateJpaUtil);
//
//        this.setBeanManager(persistentBeanManager);
//
//        log.debug("GuiceRemoteServiceServlet constructor ends!!");
//        log.info("GuiceRemoteServiceServlet constructor ends!!");
//    }

}