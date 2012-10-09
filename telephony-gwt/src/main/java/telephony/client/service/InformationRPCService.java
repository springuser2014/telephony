package telephony.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import telephony.server.core.configuration.Constant;
import telephony.server.core.entity.Store;

@RemoteServiceRelativePath(InformationRPCService.PATH)
public interface InformationRPCService extends RemoteService {

        public static final String PATH = "information";
        public static final String FULL_SERVICE_PATH = Constant.APPLICATION_URL_PREFIX + PATH;

        public Long getNumberOfDeliveries(Store store);
        public Long getNumberOfSales(Store store);

}
