package telephony.core.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ModelDao;
import telephony.core.entity.jpa.Model;

/**
 * asd.
 */
public class ModelDaoImpl extends GenericDaoImpl<Model> implements ModelDao {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * asd.
	 */
	public ModelDaoImpl() {
		super(Model.class);
	}

}
