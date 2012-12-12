package telephony.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import telephony.core.configuration.Constant;
import telephony.core.entity.Store;

import java.util.List;


/**
 * @todo Remove after migration to REST services
 */
@RemoteServiceRelativePath(StoreRPCService.PATH)
public interface StoreRPCService extends RemoteService {

    public static final String PATH = "store";
    public static final String FULL_SERVICE_PATH = Constant.APPLICATION_URL_PREFIX + PATH;

    public List<Store> fetchAllStores();
}

