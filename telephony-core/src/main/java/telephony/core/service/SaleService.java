package telephony.core.service;


import java.util.List;

import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Sale;
import telephony.core.service.dto.SessionDto;
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
    List<Sale> find(SessionDto session)
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
    void add(SessionDto session, Sale sale, List<Product> products, Long storeId, Long contactId)
    	throws SessionServiceException, SaleServiceException;
    
    /**
     * asd.
     * @param session TODO
     * @param saleToCancel asd.
     * @throws SessionServiceException asd.
     * @throws SaleServiceException asd.
     */
    void delete(SessionDto session, Sale saleToCancel) 
    	throws SessionServiceException, SaleServiceException;
    
    /**
     * asd.
     * @param session TODO
     * @param saleToUpdate asd.
     * @throws SessionServiceException asd.
     * @throws SaleServiceException asd.
     */
    void update(SessionDto session, Sale saleToUpdate)
    		throws SessionServiceException, SaleServiceException;
    
    /**
     * asd.
     * @param session TODO
     * @param saleId asd. 
     * @return asd.
     * @throws SessionServiceException asd.
     * @throws SaleServiceException asd.
     */
    Sale findById(SessionDto session, Long saleId)
    		throws SessionServiceException, SaleServiceException;

    /**
     * asd.
     * @param session TODO
     * @param label asd.
     * @return TODO
     * @throws SessionServiceException 
     */
	Sale findByLabel(SessionDto session, String label) 
			throws SessionServiceException;

}
