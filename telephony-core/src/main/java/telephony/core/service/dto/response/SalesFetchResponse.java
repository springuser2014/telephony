package telephony.core.service.dto.response;

import telephony.core.service.dto.SaleSearchDto;

import java.util.ArrayList;
import java.util.List;

public class SalesFetchResponse extends BasicResponse {

    List<SaleSearchDto> sales;
    Long countTotal;

    public SalesFetchResponse() {
        sales = new ArrayList<>();
    }

    public List<SaleSearchDto> getSales() {
        return sales;
    }

    public void setSales(List<SaleSearchDto> sales) {
        this.sales = sales;
    }

    public void addSale(SaleSearchDto sale) {

        if (!sales.contains(sale)) {
            sales.add(sale);
        }
    }

    public void removeSale(SaleSearchDto sale) {

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
