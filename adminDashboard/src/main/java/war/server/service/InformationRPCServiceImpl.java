package war.server.service;

import com.google.inject.Singleton;
import war.client.service.InformationRPCService;
import war.server.gilead.GuicePersistentRemoteServiceServlet;


@SuppressWarnings("serial")
@Singleton
public class InformationRPCServiceImpl extends GuicePersistentRemoteServiceServlet implements InformationRPCService {

    public Integer getNumberOfDeliveries() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Integer getNumberOfSales() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
