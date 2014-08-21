package telephony.core.service.impl;

import telephony.core.dao.TestEntityDao;
import telephony.core.entity.jpa.TestEntity;
import telephony.core.service.TestEntityService;

/**
 * asd.
 */
public class TestEntityServiceImpl 
extends AbstractGenericService<TestEntity, TestEntityDao> 
implements TestEntityService {

	public TestEntityServiceImpl() {
		super();
	}
	
	/**
	 * asd.
	 * @param clazz a.
	 */
	public TestEntityServiceImpl(Class<TestEntityDao> clazz) {
		super(clazz);
	}

}
