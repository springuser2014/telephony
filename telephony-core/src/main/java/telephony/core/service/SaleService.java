package telephony.core.service;


import java.util.List;

import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Sale;
import telephony.core.service.bean.Session;
import telephony.core.service.exception.SaleServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 */
public interface SaleService extends BasicService<Sale> {

    /**
     * asd.
     * @param session TODO
     * @return asd.
     * @throws SessionServiceException as.
     * @throws SaleServiceException asd.
     */
    List<Sale> find(Session session)
    	throws SessionServiceException, SaleServiceException;
    	
    /**
     * asd.
     * @param session TODO
     * @param sale asd.
     * @param products asd.
     * @param storeId TODO
     * @param contactId TODO
     * @throws SessionServiceException asd.
     * @throws SaleServiceException asd.
     */
    void add(Session session, Sale sale, List<Product> products, Long storeId, Long contactId)
    	throws SessionServiceException, SaleServiceException;
    
    /**
     * asd.
     * @param session TODO
     * @param saleToCancel asd.
     * @throws SessionServiceException asd.
     * @throws SaleServiceException asd.
     */
    void delete(Session session, Sale saleToCancel) 
    	throws SessionServiceException, SaleServiceException;
    
    /**
     * asd.
     * @param session TODO
     * @param saleToUpdate asd.
     * @throws SessionServiceException asd.
     * @throws SaleServiceException asd.
     */
    void update(Session session, Sale saleToUpdate)
    		throws SessionServiceException, SaleServiceException;
    
    /**
     * asd.
     * @param session TODO
     * @param saleId asd. 
     * @return asd.
     * @throws SessionServiceException asd.
     * @throws SaleServiceException asd.
     */
    Sale findById(Session session, Long saleId)
    		throws SessionServiceException, SaleServiceException;

    /**
     * asd.
     * @param session TODO
     * @param label asd.
     * @return TODO
     * @throws SessionServiceException 
     */
	Sale findByLabel(Session session, String label) 
			throws SessionServiceException;

}
