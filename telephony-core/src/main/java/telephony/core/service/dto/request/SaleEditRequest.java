package telephony.core.service.dto.request;

import telephony.core.service.dto.SaleEditDto;
import telephony.core.service.dto.SessionDto;

public class SaleEditRequest extends AuthRequest {

    SaleEditDto saleEdit;

    public SaleEditRequest(SessionDto session) {
        super(session);
    }

    public SaleEditDto getSaleEdit() {
        return saleEdit;
    }

    public void setSaleEdit(SaleEditDto saleEdit) {
        this.saleEdit = saleEdit;
    }
}
