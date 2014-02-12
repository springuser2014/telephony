package telephony;

import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import telephony.core.guice.env.SystemPropertyEnvironmentNameResolver;
import telephony.core.guice.env.TelephonyCoreEnvironmentResolver;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.persist.PersistService;

/**
 * Tests listener, which helps to handle with Guice-managed injections in tests.
 * It starts and stops JPA persistence service.
 *
 */
public class TelephonyCoreTestListener extends RunListener {
	
	@Override
	public void testRunFinished(Result result) throws Exception {
		
		if (BaseCoreTest.getPersistService() != null) {
			BaseCoreTest.getPersistService().stop();
		}
		
		super.testRunFinished(result);
	}

	@Override
	public void testRunStarted(Description description) throws Exception {
		
		List<AbstractModule> modules = new TelephonyCoreEnvironmentResolver()
		.resolveWith(
				new SystemPropertyEnvironmentNameResolver()
		);
	
	    BaseCoreTest.setInjector(Guice.createInjector(modules));

		BaseCoreTest.setPersistService(
				BaseCoreTest.getInjector().getInstance(PersistService.class)
		);
		
		if (BaseCoreTest.getPersistService() != null) {
			BaseCoreTest.getPersistService().start();
		}
		
		super.testRunStarted(description);
	}

}
