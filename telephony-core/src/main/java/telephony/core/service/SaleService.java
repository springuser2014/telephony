package telephony.core.service;


import java.util.List;

import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Sale;
import telephony.core.service.exception.SaleServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface SaleService extends BasicService<Sale> {

    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     * @return asd.
     */
    List<Sale> findAllSales(String username, String sessionId)
    	throws SessionServiceException, SaleServiceException;
    	
    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     * @param sale asd.
     * @param products asd.
     * @param storeId TODO
     * @param contactId TODO
     */
    void addNewSale(String username, String sessionId, Sale sale, List<Product> products, Long storeId, Long contactId)
    	throws SessionServiceException, SaleServiceException;
    
    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     * @param saleToCancel asd.
     * @throws SessionServiceException asd.
     * @throws SaleServiceException asd.
     */
    void delete(String username, String sessionId, Sale saleToCancel) 
    	throws SessionServiceException, SaleServiceException;
    
    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     * @param saleToUpdate asd.
     * @throws SessionServiceException asd.
     * @throws SaleServiceException asd.
     */
    void updateSale(String username, String sessionId, Sale saleToUpdate)
    		throws SessionServiceException, SaleServiceException;
    
    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     * @param saleId asd. 
     * @return asd.
     * @throws SessionServiceException asd.
     * @throws SaleServiceException asd.
     */
    Sale findById(String username, String sessionId, Long saleId)
    		throws SessionServiceException, SaleServiceException;

    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     * @param label
     * @return TODO
     * @throws SessionServiceException 
     */
	Sale findByLabel(String username, String sessionId, String label) throws SessionServiceException;

}
