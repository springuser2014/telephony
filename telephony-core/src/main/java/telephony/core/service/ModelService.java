package telephony.core.service;

import telephony.core.entity.jpa.Model;

/**
 * asd.
 */
public interface ModelService extends BasicService<Model> {
	
	/**
	 * asd.
	 * @param label a.
	 * @return a.
	 */
	Model findByLabel(String label);

}
