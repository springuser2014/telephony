package telephony.core.service.dto.request;

import java.util.Date;
import java.util.List;

/**
 * sad.
 */
public class SaleAddRequestDto extends AuthRequestDto {	
	
	private String label;
	
	private Long storeId;	
	private Date dateIn;	
	private Long contactId;
	
	private List<Long> productIds;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public List<Long> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<Long> productIds) {
		this.productIds = productIds;
	}
	
	public void addProductId(Long id) {
		if (!this.productIds.contains(id)) {
			this.productIds.add(id);		
		}
	}
	
	public void removeProductId(Long id) {
		if (this.productIds.contains(id)) {
			this.productIds.remove(id);		
		}
	}
}
