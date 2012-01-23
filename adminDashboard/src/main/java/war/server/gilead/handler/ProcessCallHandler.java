package war.server.gilead.handler;

import com.google.gwt.user.client.rpc.SerializationException;
import war.server.gilead.adapter.RemoteServiceAdapter;

public interface ProcessCallHandler<A extends RemoteServiceAdapter> {
    String processCall(String payload, A adapter) throws SerializationException;
}
