package telephony.core.service;

import telephony.core.entity.jpa.Sale;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;
import telephony.core.service.exception.SaleServiceException;
import telephony.core.service.exception.SessionServiceException;

public interface SaleService extends BasicService<Sale> {

    SaleDetailsResponse fetchDetails(SaleDetailsRequest request)
            throws SessionServiceException;

    SalesFetchResponse findSales(SalesFetchRequest request)
        throws SessionServiceException, SaleServiceException;

    SaleAddResponse add(SaleAddRequest request)
        throws SessionServiceException, SaleServiceException;

    SaleEditResponse edit(SaleEditRequest request)
        throws SessionServiceException, SaleServiceException;

    SaleDeleteResponse delete(SaleDeleteRequest request)
        throws SessionServiceException, SaleServiceException;

}
