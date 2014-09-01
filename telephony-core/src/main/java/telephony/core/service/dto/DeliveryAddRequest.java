package telephony.core.service.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class DeliveryAddRequest {
	
	private String username;
	private String sessionId;

	private String label;
	private String dateIn;
	
	private Long storeId;
	private Long contactId;
	
	private List<ProductBean> products;

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

	public List<ProductBean> getProducts() {
		return products;
	}

	public void setProducts(List<ProductBean> products) {
		this.products = products;
	}
	
	public void addProduct(ProductBean product) {
		
		if (!this.products.contains(product)) {
			this.products.add(product);
		}
	}
	
	public void removeProduct(ProductBean product) {
		
		if (this.products.contains(product)) {
			this.products.remove(product);
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
}
