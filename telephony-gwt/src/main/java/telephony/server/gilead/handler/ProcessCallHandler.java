package telephony.server.gilead.handler;

import com.google.gwt.user.client.rpc.SerializationException;
import telephony.server.gilead.adapter.RemoteServiceAdapter;

public interface ProcessCallHandler<A extends RemoteServiceAdapter> {
    public String processCall(String payload, A adapter) throws SerializationException;
}
