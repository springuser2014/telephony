package telephony.core.service.interfaces;


import telephony.core.entity.Store;

public interface InformationService {

    public long getNumberOfDeliveries(Store store);

    public long getNumberOfSales(Store store);
}
