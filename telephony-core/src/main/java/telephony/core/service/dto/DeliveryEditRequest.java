package telephony.core.service.dto;

import java.util.List;

public class DeliveryEditRequest {
	
	private String sessionId;
	
	private String username;

	private String label;
	private String dateIn;
	
	private Long storeId;
	private Long contactId;
	
	private List<ProductBean> productsToAdd;
	
	private List<ProductEditBean> productsToEdit;
	
	private List<Long> productsToDelete;

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
	
	public void addProductToAdd(ProductBean id) {
		
		if (!this.productsToAdd.contains(id)) {
			this.productsToAdd.add(id);
		}
	}
	
	public void removeProductToAdd(ProductBean id) {
		
		if (this.productsToAdd.contains(id)) {
			this.productsToAdd.remove(id);
		}
	}
	
	public void addProductToEdit(ProductEditBean id) {
		
		if (!this.productsToEdit.contains(id)) {
			this.productsToEdit.add(id);
		}
	}
	
	public void removeProductToDelete(ProductEditBean id) {
		
		if (this.productsToEdit.contains(id)) {
			this.productsToEdit.remove(id);
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
}
