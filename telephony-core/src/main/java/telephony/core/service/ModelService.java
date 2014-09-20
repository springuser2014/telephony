package telephony.core.service;

import java.util.Collection;
import java.util.List;

import telephony.core.entity.jpa.Model;
import telephony.core.service.dto.SessionDto;

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
	Model findByLabel(SessionDto session, String label);

	/**
	 * asd.
	 * @param session TODO
	 * @param id a.
	 * @return d.
	 */
	Model findById(SessionDto session, long id);

	/**
	 * asd.
	 * @param session TODO
	 * @param ids a.
	 * @return a.
	 */
	Collection<Model> findByIds(SessionDto session, List<Long> ids);

	/**
	 * asd.
	 * @param session TODO
	 * @param model a.
	 * @return asd.
	 */
	Model update(SessionDto session, Model model);

	/**
	 * asd.
	 * @param session TODO
	 * @param coll a.
	 * @return ad.
	 */
	Collection<Model> update(SessionDto session, Collection<Model> coll);

	/**
	 * asd.
	 * @param session TODO
	 * @param model a.
	 */
	void removeById(SessionDto session, Long model);

	/**
	 * asd.
	 * @param session TODO
	 * @param model a.
	 */
	void removeById(SessionDto session, Collection<Long> model);

	/**
	 * asd.
	 * @param session TODO
	 * @param model a.
	 * 
	 */
	void remove(SessionDto session, Model model);

}
