package war.server.guice;

import com.google.gwt.user.client.rpc.SerializationException;

public interface ProcessCallHandler<A extends RemoteServiceAdapter> {
    String processCall(String payload, A adapter) throws SerializationException;
}
