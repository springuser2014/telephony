package telephony.core.service;

import java.util.Collection;
import java.util.List;

import telephony.core.entity.jpa.Model;
import telephony.core.service.bean.Session;

/**
 * asd.
 */
public interface ModelService extends BasicService<Model> {
	
	/**
	 * asd.
	 * @param session TODO
	 * @param label a.
	 * @return a.
	 */
	Model findByLabel(Session session, String label);

	/**
	 * asd.
	 * @param session TODO
	 * @param id a.
	 * @return d.
	 */
	Model findById(Session session, long id);

	/**
	 * asd.
	 * @param session TODO
	 * @param ids a.
	 * @return a.
	 */
	Collection<Model> findByIds(Session session, List<Long> ids);

	/**
	 * asd.
	 * @param session TODO
	 * @param model a.
	 * @return asd.
	 */
	Model update(Session session, Model model);

	/**
	 * asd.
	 * @param session TODO
	 * @param coll a.
	 * @return ad.
	 */
	Collection<Model> update(Session session, Collection<Model> coll);

	/**
	 * asd.
	 * @param session TODO
	 * @param model a.
	 */
	void removeById(Session session, Long model);

	/**
	 * asd.
	 * @param session TODO
	 * @param model a.
	 */
	void removeById(Session session, Collection<Long> model);

	/**
	 * asd.
	 * @param session TODO
	 * @param model a.
	 * 
	 */
	void remove(Session session, Model model);

}
