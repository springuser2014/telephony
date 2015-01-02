package telephony.core.service;


import java.util.List;

import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Sale;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.exception.SaleServiceException;
import telephony.core.service.exception.SessionServiceException;

/**
 * asd.
 */
public interface SaleService extends BasicService<Sale> {

    /**
     * sad.
     * @param request a.
     * @return a.
     */
    SaleDetailsResponse fetchDetails(SaleDetailsRequest request) throws SessionServiceException;

    /**
     * asd.
     * @param request a.
     * @return d.
     * @throws SessionServiceException a.
     * @throws SaleServiceException d.
     */
    SalesFetchResponse findSales(SalesFetchRequest request)
        throws SessionServiceException, SaleServiceException;

    /**
     * asd.
     * @param request a.
     * @return a.
     * @throws SessionServiceException a.
     * @throws SaleServiceException d.
     */
    SaleAddResponse add(SaleAddRequest request)
        throws SessionServiceException, SaleServiceException;

    /**
     * asd.
     * @param request a.
     * @return d.
     * @throws SessionServiceException a.
     * @throws SaleServiceException d.
     */
    SaleEditResponse edit(SaleEditRequest request)
        throws SessionServiceException, SaleServiceException;

    /**
     * asd.
     * @param request a.
     * @return d.
     * @throws SessionServiceException a.
     * @throws SaleServiceException d.
     */
    SaleDeleteResponse delete(SaleDeleteRequest request)
        throws SessionServiceException, SaleServiceException;

    ////////////////////////////////
    // TODO remove the stuff below
    ////////////////////////////////

    /**
     * asd.
     * @param session TODO
     * @return asd.
     * @throws SessionServiceException as.
     * @throws SaleServiceException asd.
     */
    @Deprecated
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
    @Deprecated
    void add(SessionDto session, Sale sale, List<Product> products, Long storeId, Long contactId)
    	throws SessionServiceException, SaleServiceException;
    
    /**
     * asd.
     * @param session TODO
     * @param saleToCancel asd.
     * @throws SessionServiceException asd.
     * @throws SaleServiceException asd.
     */
    @Deprecated
    void delete(SessionDto session, Sale saleToCancel) 
    	throws SessionServiceException, SaleServiceException;
    
    /**
     * asd.
     * @param session TODO
     * @param saleToUpdate asd.
     * @throws SessionServiceException asd.
     * @throws SaleServiceException asd.
     */
    @Deprecated
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
    @Deprecated
    Sale findById(SessionDto session, Long saleId)
    		throws SessionServiceException, SaleServiceException;

    /**
     * asd.
     * @param session TODO
     * @param label asd.
     * @return TODO
     * @throws SessionServiceException 
     */
    @Deprecated
	Sale findByLabel(SessionDto session, String label) 
			throws SessionServiceException;

}
