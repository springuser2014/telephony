package telephony.server.gilead;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.inject.Inject;
import com.google.inject.Injector;
import net.sf.gilead.gwt.PersistentRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.server.gilead.adapter.JPARemoteServiceAdapter;
import telephony.server.gilead.handler.JPAProcessCallHandler;

import javax.servlet.http.HttpServletRequest;


/**
 * Improved version of gilead's PersistentRemoteService servlet.
 * Added guice integration with JPA support.
 *
 * Thanks to
 *
 * @link http://code.google.com/p/gwt-enterprise-patterns/
 */
@SuppressWarnings("serial")
public abstract class GuicePersistentRemoteServiceServlet extends PersistentRemoteService implements JPARemoteServiceAdapter {

    private static Logger logger = LoggerFactory.getLogger(GuicePersistentRemoteServiceServlet.class);

    @Inject
    private Injector injector;

    @Inject
    private JPAProcessCallHandler processCallHandler;

    /**
     * Process call override. Use RPC_Copy instead. Copied from EngineRemoteService to change target object
     * from this to service
     */
    @Override
    public String processCall(String payload) throws SerializationException {
        logger.debug("processCall() : {}", payload);

        getBeanManager().getPersistenceUtil().openSession(); //TODO: figure out why I have to do this
        return processCallHandler.processCall(payload, this);
    }

    @SuppressWarnings("unchecked")
    public RemoteService getRemoteService(Class serviceClass) {
        logger.debug("getRemoteService() : {}", serviceClass.getName());

        return (RemoteService) injector.getInstance(serviceClass);
    }

    public HttpServletRequest getRequest() {
        return super.getThreadLocalRequest();
    }

    @Override
    public void onAfterRequestDeserialized(RPCRequest rpcRequest) {
        logger.debug("onAfterRequestDeserialized() : {}", rpcRequest.getClass().getName());

        super.onAfterRequestDeserialized(rpcRequest);
    }
}