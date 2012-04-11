package war.server.core.service.interfaces;


import war.server.core.entity.Store;

public interface InformationService {

    public long getNumberOfDeliveries(Store store);
    public long getNumberOfSales(Store store);
}
