package telephony.core.service;

import java.util.Collection;
import java.util.List;

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

	/**
	 * asd.
	 * @param id a.
	 * @return d.
	 */
	Model findById(long id);

	/**
	 * asd.
	 * @param ids a.
	 * @return a.
	 */
	Collection<Model> findByIds(List<Long> ids);

	/**
	 * asd.
	 * @param model a.
	 * @return asd.
	 */
	Model update(Model model);

	/**
	 * asd.
	 * @param coll a.
	 * @return ad.
	 */
	Collection<Model> update(Collection<Model> coll);

	/**
	 * asd.
	 * @param model a.
	 */
	void removeById(Long model);

	/**
	 * asd.
	 * @param model a.
	 */
	void removeById(Collection<Long> model);

	/**
	 * asd.
	 * @param model a.
	 * 
	 */
	void remove(Model model);

}
