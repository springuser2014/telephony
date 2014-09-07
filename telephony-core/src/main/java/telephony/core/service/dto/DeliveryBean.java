package telephony.core.service.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DeliveryBean {

	private Long id;
	private String label;
	private String dateIn;
	private Long storeId;
	private Long contactId;

	private List<ProductBean> products;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		if (!products.contains(product)) {
			products.add(product);
		}
	}

	public void removeProduct(ProductBean product) {
		if (products.contains(product)) {
			products.remove(product);
		}
	}
}
