package telephony.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import telephony.core.configuration.Constant;
import telephony.core.entity.Store;

/**
 * @todo Remove after migration to REST services
 */
@RemoteServiceRelativePath(InformationRPCService.PATH)
public interface InformationRPCService extends RemoteService {

    public static final String PATH = "information";
    public static final String FULL_SERVICE_PATH = Constant.APPLICATION_URL_PREFIX + PATH;

    public Long getNumberOfDeliveries(Store store);

    public Long getNumberOfSales(Store store);

}
