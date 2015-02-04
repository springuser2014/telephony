package telephony.core.dao;

import telephony.core.entity.jpa.Model;
import telephony.core.query.filter.ModelFilterCriteria;

import java.util.List;

public interface ModelDao extends GenericDao<Model> {

	Model findByLabel(String label);

	List<Model> findByCriteria(ModelFilterCriteria filters);
}
