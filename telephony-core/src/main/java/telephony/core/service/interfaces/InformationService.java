package telephony.core.service.interfaces;


import telephony.core.entity.Store;

/**
 * asd.
 *
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
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
