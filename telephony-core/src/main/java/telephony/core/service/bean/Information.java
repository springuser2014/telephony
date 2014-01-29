package telephony.core.service.bean;

import java.util.List;

import telephony.core.entity.jpa.Role;

/**
 * Represents a bean containing basic information about system status.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class Information {
	
	private long numberOfProducts;
	private long numberOfStores;
	private long numberOfUsers;
	private List<Role> roles;
	
	
	

}
