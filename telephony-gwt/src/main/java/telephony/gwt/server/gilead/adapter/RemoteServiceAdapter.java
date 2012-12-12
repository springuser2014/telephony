package telephony.gwt.server.gilead.adapter;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.SerializationPolicyProvider;

import javax.servlet.http.HttpServletRequest;

/**
 * @todo Remove after migration to REST services
 */
public interface RemoteServiceAdapter extends SerializationPolicyProvider {
    public RemoteService getRemoteService(Class<?> serviceClass);

    public void onAfterRequestDeserialized(RPCRequest req);

    public HttpServletRequest getRequest();
}
