package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.TaxDto;

public class TaxAddRequest extends AuthRequest {

    TaxDto taxDto;

    public TaxAddRequest() {
        super();
    }

    public TaxAddRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public TaxDto getTaxDto() {
        return taxDto;
    }

    public void setTaxDto(TaxDto taxDto) {
        this.taxDto = taxDto;
    }
}
