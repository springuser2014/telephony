package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class SaleDeleteRequest extends AuthRequest {

    Long saleId;

    public SaleDeleteRequest() {
        super();
    }

    public SaleDeleteRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }
}
