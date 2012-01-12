package war.server.guice;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.SerializationPolicyProvider;

import javax.servlet.http.HttpServletRequest;

public interface RemoteServiceAdapter extends SerializationPolicyProvider {
    RemoteService getRemoteService(Class<?> serviceClass);
    void onAfterRequestDeserialized(RPCRequest req);
    HttpServletRequest getRequest();
}
