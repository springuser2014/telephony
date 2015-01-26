package telephony.core.query.filter;

import java.util.ArrayList;
import java.util.List;

public class SaleComplaintFilterCriteria
extends ComplaintFilterCriteria {

    List<Long> salesIds;

    public SaleComplaintFilterCriteria() {
        salesIds = new ArrayList<Long>();
    }

    public List<Long> getSalesIds() {
        return salesIds;
    }

    public void setSalesIds(List<Long> salesIds) {
        this.salesIds = salesIds;
    }

    public void addSaleId(Long saleId) {

        if (!salesIds.contains(saleId)) {
            salesIds.add(saleId);
        }
    }

    public void removeSaleId(Long saleId) {

        if (salesIds.contains(saleId)) {
            salesIds.remove(saleId);
        }
    }
}
