package telephony;

import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import telephony.core.guice.env.SystemPropertyEnvironemntNameResolver;
import telephony.core.guice.env.TelephonyCoreEnvironmentResolver;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.persist.PersistService;

public class TelephonyCoreTestListener extends RunListener {
	
	@Override
	public void testRunFinished(Result result) throws Exception {
		
		if (BaseCoreTest.persistService != null) {
			BaseCoreTest.persistService.stop();
		}
		
		super.testRunFinished(result);
	}

	@Override
	public void testRunStarted(Description description) throws Exception {
		
		List<AbstractModule> modules = new TelephonyCoreEnvironmentResolver()
		.resolveWith(
				new SystemPropertyEnvironemntNameResolver()
		);
	
	    BaseCoreTest.injector = Guice.createInjector(modules);

		BaseCoreTest.persistService = BaseCoreTest.injector.getInstance(PersistService.class);
		
		if (BaseCoreTest.persistService != null) {
			BaseCoreTest.persistService.start();
		}
		
		super.testRunStarted(description);
	}

}
