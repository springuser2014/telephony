package war.server.guice;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.inject.Inject;
import com.google.inject.Injector;
import net.sf.gilead.gwt.PersistentRemoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


@SuppressWarnings("serial")
public abstract class GuiceRemoteServiceServlet extends PersistentRemoteService implements JPARemoteServiceAdapter {

    private static Logger logger = LoggerFactory.getLogger(GuiceRemoteServiceServlet.class);

    private static final long serialVersionUID = 68052617558196206L;

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
        getBeanManager().getPersistenceUtil().openSession(); //TODO: figure out why I have to do this
        return processCallHandler.processCall(payload, this);
    }

    @SuppressWarnings("unchecked")
    public RemoteService getRemoteService(Class serviceClass) {
        return (RemoteService) injector.getInstance(serviceClass);
    }

    @Override()
    public HttpServletRequest getRequest() {
        return super.getThreadLocalRequest();
    }

    @Override
    public void onAfterRequestDeserialized(RPCRequest rpcRequest) {
        super.onAfterRequestDeserialized(rpcRequest);
    }
}