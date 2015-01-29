package telephony.core.service.dto.response;

import telephony.core.service.dto.TaxDto;

import java.util.List;

public class TaxFetchResponse extends BasicResponse {

    List<TaxDto> taxes;
    Long countTotal;

    public List<TaxDto> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<TaxDto> taxes) {
        this.taxes = taxes;
    }

    public Long getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(Long countTotal) {
        this.countTotal = countTotal;
    }
}
