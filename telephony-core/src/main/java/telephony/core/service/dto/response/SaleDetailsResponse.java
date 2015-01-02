package telephony.core.service.dto.response;

import telephony.core.service.dto.DetailedSaleDto;

public class SaleDetailsResponse extends BasicResponse {

    DetailedSaleDto detailedSale;

    public DetailedSaleDto getDetailedSale() {
        return detailedSale;
    }

    public void setDetailedSale(DetailedSaleDto detailedSale) {
        this.detailedSale = detailedSale;
    }
}
