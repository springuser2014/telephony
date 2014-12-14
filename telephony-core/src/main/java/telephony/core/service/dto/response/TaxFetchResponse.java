package telephony.core.service.dto.response;

import telephony.core.service.dto.TaxDto;

import java.util.List;

public class TaxFetchResponse extends BasicResponse {

    List<TaxDto> taxes;

    public List<TaxDto> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<TaxDto> taxes) {
        this.taxes = taxes;
    }
}
