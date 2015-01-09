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

}
