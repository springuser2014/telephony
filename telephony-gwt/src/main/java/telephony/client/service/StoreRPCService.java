package telephony.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import telephony.server.core.configuration.Constant;
import telephony.server.core.entity.Store;

import java.util.List;


@RemoteServiceRelativePath(StoreRPCService.PATH)
public interface StoreRPCService extends RemoteService {

    public static final String PATH = "store";
    public static final String FULL_SERVICE_PATH = Constant.APPLICATION_URL_PREFIX + PATH;

    public List<Store> fetchAllStores();
}

