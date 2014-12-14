package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class TaxDeleteRequest extends AuthRequest {

    Long taxId;

    public TaxDeleteRequest() {
        super();
    }

    public TaxDeleteRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }
}
