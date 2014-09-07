package telephony.core.service.dto;

import java.util.ArrayList;
import java.util.List;

public class DeliveryEditRequest {
	
	private String sessionId;
	
	private String username;
	
	private Long id;

	private String label;
	private String dateIn;
	
	private Long storeId;
	private Long contactId;
	
	private List<ProductBean> productsToAdd = new ArrayList<ProductBean>();
	
	private List<ProductEditBean> productsToEdit = new ArrayList<ProductEditBean>();
	
	private List<Long> productsToDelete = new ArrayList<Long>();

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
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

	public List<ProductBean> getProductsToAdd() {
		return productsToAdd;
	}

	public void setProductsToAdd(List<ProductBean> productsToAdd) {
		this.productsToAdd = productsToAdd;
	}
	
	public void addProductToAdd(ProductBean product) {
		
		if (!this.productsToAdd.contains(product)) {
			this.productsToAdd.add(product);
		}
	}
	
	public void removeProductToAdd(ProductBean product) {
		
		if (this.productsToAdd.contains(product)) {
			this.productsToAdd.remove(product);
		}
	}
	
	public void addProductToEdit(ProductEditBean product) {
		
		if (!this.productsToEdit.contains(product)) {
			this.productsToEdit.add(product);
		}
	}
	
	public void removeProductToDelete(ProductEditBean product) {
		
		if (this.productsToEdit.contains(product)) {
			this.productsToEdit.remove(product);
		}
	}

	public List<ProductEditBean> getProductsToEdit() {
		return productsToEdit;
	}

	public void setProductsToEdit(List<ProductEditBean> productsToEdit) {
		this.productsToEdit = productsToEdit;
	}
	
	public void addProductToDelete(Long id) {
		
		if (!this.productsToDelete.contains(id)) {
			this.productsToDelete.add(id);
		}
	}
	
	public void removeProductToDelete(Long id) {
		
		if (this.productsToDelete.contains(id)) {
			this.productsToDelete.remove(id);
		}
	}

	public List<Long> getProductsToDelete() {
		return productsToDelete;
	}

	public void setProductsToDelete(List<Long> productsToDelete) {
		this.productsToDelete = productsToDelete;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
