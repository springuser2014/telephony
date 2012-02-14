package war.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import war.server.core.configuration.Constant;

@RemoteServiceRelativePath(DeliveryRPCService.PATH)
public interface DeliveryRPCService extends RemoteService {

    public static final String PATH = "delivery";
    public static final String FULL_SERVICE_PATH = Constant.APPLICATION_URL_PREFIX + PATH;
}
