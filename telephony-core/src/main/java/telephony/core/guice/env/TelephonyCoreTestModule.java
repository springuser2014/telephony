package telephony.core.guice.env;

import telephony.core.dao.GenericDao;
import telephony.core.dao.ProductComplaintDao;
import telephony.core.dao.TestEntityDao;
import telephony.core.dao.impl.GenericDaoImpl;
import telephony.core.dao.impl.TestEntityDaoImpl;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.entity.jpa.TestEntity;
import telephony.core.guice.TelephonyCoreServicesModule;
import telephony.core.query.filter.ProductComplaintFilterCriteria;
import telephony.core.service.ComplaintService;
import telephony.core.service.GenericService;
import telephony.core.service.TestEntityService;
import telephony.core.service.dto.ProductComplaintDto;
import telephony.core.service.dto.ProductComplaintEditDto;
import telephony.core.service.dto.ProductDetailedComplaintDto;
import telephony.core.service.dto.request.ProductComplaintDetailsFetchRequest;
import telephony.core.service.dto.request.ProductComplaintEditRequest;
import telephony.core.service.dto.request.ProductComplaintFetchRequest;
import telephony.core.service.dto.request.ReportProductComplaintRequest;
import telephony.core.service.dto.response.ProductComplaintDetailsFetchResponse;
import telephony.core.service.dto.response.ProductComplaintEditResponse;
import telephony.core.service.dto.response.ProductComplaintFetchResponse;
import telephony.core.service.dto.response.ReportProductComplaintResponse;
import telephony.core.service.impl.AbstractComplaintService;
import telephony.core.service.impl.AbstractGenericService;
import telephony.core.service.impl.TestEntityServiceImpl;

import com.google.inject.AbstractModule;
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
		
		// class bindings just for tests purposes
		bind(TestEntityDao.class).to(TestEntityDaoImpl.class);
		bind(TestEntityService.class).to(TestEntityServiceImpl.class);
		
		
		bind(new TypeLiteral<GenericService<TestEntity>>() { })
		.toInstance(
			new AbstractGenericService<TestEntity, TestEntityDao>(TestEntityDao.class) { }
		);

		bind(new TypeLiteral<GenericDao<TestEntity>>() { })
		.toInstance(
			new GenericDaoImpl<TestEntity>(TestEntity.class) { }
		);
		
		bind(new TypeLiteral<ComplaintService<ProductComplaint,
			ProductComplaintDetailsFetchResponse, ProductComplaintDetailsFetchRequest, ProductDetailedComplaintDto,
			ReportProductComplaintResponse, ReportProductComplaintRequest, ProductComplaintDto,
			ProductComplaintEditResponse, ProductComplaintEditRequest, ProductComplaintEditDto,
			ProductComplaintFetchResponse, ProductComplaintFetchRequest, ProductComplaintFilterCriteria>
		>() { })
		.toInstance(
			new AbstractComplaintService<ProductComplaint, ProductComplaintDao,
					ProductComplaintDetailsFetchResponse, ProductComplaintDetailsFetchRequest, ProductDetailedComplaintDto,
					ReportProductComplaintResponse, ReportProductComplaintRequest, ProductComplaintDto,
					ProductComplaintEditResponse, ProductComplaintEditRequest, ProductComplaintEditDto,
					ProductComplaintFetchResponse, ProductComplaintFetchRequest, ProductComplaintFilterCriteria>(ProductComplaintDao.class) { }
		);

		
		install(new TelephonyCoreServicesModule());
		install(new JpaPersistModule(PERSISTENCE_TEST));
	}
}
