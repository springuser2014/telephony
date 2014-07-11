package telephony.core.dao.impl;

import javax.persistence.Query;

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

	@Override
	public Model findByLabel(String label) {
		
        logger.debug("ModelDaoImpl.findByLabel starts");

        String [] strs = label.split(" ");
        
        String or = ""; 
        
        {
        	int i = 1;
	        for (String s : strs) {
	        	or += " m.label LIKE ?" + i;
	        	
	        	if (i != strs.length) {
	        		or += " OR ";
	        	}
	        	
	        	i++;
	        }
        }
        
		Query q =
			getEntityManager().createQuery(
        	  " select m from Model m "
            + " where " + or);
		
		int i = 1;
		for (String s : strs) {
			q.setParameter(i, s);
			i++;
		}
		
		Model result = (Model) q.getSingleResult();
		
        logger.debug(" found {} element", result);

		return result;
	}

}
