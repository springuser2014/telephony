package telephony.core.dao;

import java.util.Collection;
import java.util.List;

import telephony.core.entity.jpa.Model;
import telephony.core.query.filter.ModelFilterCriteria;

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

	/**
	 * asd.
	 * @param filters a.
	 * @return a.
	 */
	List<Model> find(ModelFilterCriteria filters);
}
