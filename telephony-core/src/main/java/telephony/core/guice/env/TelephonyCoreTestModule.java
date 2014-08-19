package telephony.core.guice.env;

import telephony.core.dao.GenericDao;
import telephony.core.dao.TestEntityDao;
import telephony.core.dao.impl.GenericDaoImpl;
import telephony.core.dao.impl.TestEntityDaoImpl;
import telephony.core.entity.jpa.Tax;
import telephony.core.entity.jpa.TestEntity;
import telephony.core.guice.TelephonyCoreServicesModule;
import telephony.core.service.GenericService;
import telephony.core.service.TestEntityService;
import telephony.core.service.impl.AbstractBasicService;
import telephony.core.service.impl.AbstractGenericService;
import telephony.core.service.impl.TestEntityServiceImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import com.google.inject.persist.jpa.JpaPersistModule;

/**
 * asd.
 */
@Environment("TEST")
public class TelephonyCoreTestModule extends AbstractModule {
	
	public  static final String PERSISTENCE_TEST = "telephony-test";
	private static final Integer SESSION_VALIDITY = new Integer(30 * 60 * 1000);
	
	@Override
	protected void configure() {
		
		bind(Integer.class)
		.annotatedWith(Names.named("sessionValidity"))
		.toInstance(SESSION_VALIDITY);
		
		bind(TestEntityDao.class).to(TestEntityDaoImpl.class);
		bind(TestEntityService.class).to(TestEntityServiceImpl.class);
		
		bind(new TypeLiteral<GenericService<TestEntity>>() { })
		.toInstance(
			new AbstractGenericService<TestEntity, TestEntityDao>() { }
		);

		bind(new TypeLiteral<GenericDao<TestEntity>>() { })
		.toInstance(
			new GenericDaoImpl<TestEntity>(TestEntity.class) { }
		);
				
		install(new TelephonyCoreServicesModule());
		install(new JpaPersistModule(PERSISTENCE_TEST));
	}
	

	
	
}
