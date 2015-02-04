package telephony.core.dao;

import java.util.List;

import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.StoreFilterCriteria;

public interface StoresDao extends GenericDao<Store> {

	Store findByLabel(String storelabel);

	List<Store> findByCriteria(StoreFilterCriteria filters);

	Long countByCriteria(StoreFilterCriteria filters);
}
