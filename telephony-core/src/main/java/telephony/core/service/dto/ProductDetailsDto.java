package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetailsDto extends ProductFetchDto {

    private DeliverySearchDto delivery;

    private SaleSearchDto sale;

    private List<ProductComplaintDto> productComplaints;

    private List<SaleComplaintDto> saleComplaints;

    public ProductDetailsDto() {
        productComplaints = new ArrayList<>();
        saleComplaints = new ArrayList<>();
    }

    public DeliverySearchDto getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliverySearchDto delivery) {
        this.delivery = delivery;
    }

    public SaleSearchDto getSale() {
        return sale;
    }

    public void setSale(SaleSearchDto sale) {
        this.sale = sale;
    }

    public List<ProductComplaintDto> getProductComplaints() {
        return productComplaints;
    }

    public void setProductComplaints(List<ProductComplaintDto> productComplaints) {
        this.productComplaints = productComplaints;
    }

    public void addProductComplaint(ProductComplaintDto dto) {

        if (!productComplaints.contains(dto)) {
            productComplaints.add(dto);
        }
    }

    public void removeProductComplaint(ProductComplaintDto dto) {
        if (productComplaints.contains(dto)) {
            productComplaints.remove(dto);
        }
    }

    public List<SaleComplaintDto> getSaleComplaints() {
        return saleComplaints;
    }

    public void setSaleComplaints(List<SaleComplaintDto> saleComplaints) {
        this.saleComplaints = saleComplaints;
    }

    public void addSaleComplaint(SaleComplaintDto dto) {

        if (!saleComplaints.contains(dto)) {
            saleComplaints.add(dto);
        }
    }

    public void removeSaleComplaint(SaleComplaintDto dto) {
        if (saleComplaints.contains(dto)) {
            saleComplaints.remove(dto);
        }
    }
}
