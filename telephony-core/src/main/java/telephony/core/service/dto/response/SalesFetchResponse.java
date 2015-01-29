package telephony.core.service.dto.response;

import telephony.core.service.dto.SaleDto;

import java.util.ArrayList;
import java.util.List;

public class SalesFetchResponse extends BasicResponse {

    List<SaleDto> sales;
    Long countTotal;

    public SalesFetchResponse() {
        sales = new ArrayList<>();
    }

    public List<SaleDto> getSales() {
        return sales;
    }

    public void setSales(List<SaleDto> sales) {
        this.sales = sales;
    }

    public void addSale(SaleDto sale) {

        if (!sales.contains(sale)) {
            sales.add(sale);
        }
    }

    public void removeSale(SaleDto sale) {

        if (sales.contains(sale)) {
            sales.remove(sale);
        }
    }

    public Long getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(Long countTotal) {
        this.countTotal = countTotal;
    }
}
