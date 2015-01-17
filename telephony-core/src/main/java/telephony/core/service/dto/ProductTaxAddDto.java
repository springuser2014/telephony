package telephony.core.service.dto;

import java.util.Date;

public class ProductTaxAddDto {

    private Long taxId;
    private Date from;

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }
}
