package telephony.core.system;

import java.util.ArrayList;
import java.util.List;

import telephony.core.entity.jpa.Store;

/**
 * asd.
 */
public enum SystemRole {
	
	USERS_VIEW,
	USERS_MANAGEMENT,
	
	PRODUCTS_VIEW,
	PRODUCTS_MANAGEMENT,
	
	SALES_MANAGEMENT,
	SALES_VIEW,
	
	DELIVERIES_MANAGEMENT,
	DELIVERIES_VIEW,
	
	CONTACTS_MANAGEMENT,
	CONTACTS_VIEW,
	
	COMPLAINTS_MANAGEMENT,
	COMPLAINTS_VIEW;
	
	/**
	 * asd.
	 * @param store a.
	 * @return a.
	 */
	public static List<String> createSystemRoleFor(Store store) {
		
		List<String> lst = new ArrayList<String>();
		
		for (SystemRole r : SystemRole.values()) {
			lst.add(r.name() + "_" + store.getId());
		}
		
		return lst;
	}

}
