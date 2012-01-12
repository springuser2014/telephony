package war.server.guice.gilead.renewed.handler;

import com.google.gwt.user.client.rpc.SerializationException;
import war.server.guice.gilead.renewed.adapter.RemoteServiceAdapter;

public interface ProcessCallHandler<A extends RemoteServiceAdapter> {
    String processCall(String payload, A adapter) throws SerializationException;
}
