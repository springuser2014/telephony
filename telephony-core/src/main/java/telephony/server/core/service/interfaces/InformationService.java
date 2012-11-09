package telephony.server.core.service.interfaces;


import telephony.server.core.entity.Store;

public interface InformationService {

    public long getNumberOfDeliveries(Store store);

    public long getNumberOfSales(Store store);
}
