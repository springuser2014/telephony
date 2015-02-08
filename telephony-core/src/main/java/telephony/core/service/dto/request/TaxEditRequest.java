package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.TaxDto;

public class TaxEditRequest extends AuthRequest {

    TaxDto taxDto;
    Long newTaxId;

    public TaxEditRequest() {
        super();
    }

    public TaxEditRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public TaxDto getTaxDto() {
        return taxDto;
    }

    public void setTaxDto(TaxDto taxDto) {
        this.taxDto = taxDto;
    }

    public Long getNewTaxId() {
        return newTaxId;
    }

    public void setNewTaxId(Long newTaxId) {
        this.newTaxId = newTaxId;
    }
}
