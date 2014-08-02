package telephony.core.dao;

import java.util.Collection;

import telephony.core.entity.jpa.Model;

/**
 * asd.
 */
public interface ModelDao extends GenericDao<Model> {
	
	/**
	 * asd.
	 * @param label a.
	 * @return a.
	 */
	Model findByLabel(String label);	

}
