package telephony.core.service;


import telephony.core.entity.jpa.Store;

/**
 * asd.
 */
public interface InformationService {

    /**
     * asd.
     * @param store asd.
     * @return asd.
     */
    long getNumberOfDeliveries(Store store);

    /**
     * asd.
     * @param store asd.
     * @return asd.
     */
    long getNumberOfSales(Store store);
}
