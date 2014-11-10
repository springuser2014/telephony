package telephony.core.service.dto.request;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import telephony.core.service.dto.ProductDto;

public class DeliveryAddRequestDto extends AuthRequestDto {
	
	private String label;
	private String dateIn;
	
	private Long storeId;
	private Long contactId;
	
	private List<ProductDto> products;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"); // TODO to const
		this.dateIn = sdf.format(dateIn);
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
	
	public void addProduct(ProductDto product) {
		
		if (!this.products.contains(product)) {
			this.products.add(product);
		}
	}
	
	public void removeProduct(ProductDto product) {
		
		if (this.products.contains(product)) {
			this.products.remove(product);
		}
	}
}
