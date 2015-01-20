package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class SaleDetailsRequest extends AuthRequest {

    Long saleId;

    public SaleDetailsRequest() {
        super();
    }

    public SaleDetailsRequest(SessionDto session) {
        super(session);
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }
}
