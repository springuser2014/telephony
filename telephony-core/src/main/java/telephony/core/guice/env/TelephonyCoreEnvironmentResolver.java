package telephony.core.guice.env;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.inject.AbstractModule;

/**
 * asd.
 */
public class TelephonyCoreEnvironmentResolver {
	
	// TODO : there is a need to modify this class
	/**
	 * asd.
	 * @param nameResolver bar.
	 * @return foo.
	 */
	public List<AbstractModule> resolveWith(EnvironmentNameResolver nameResolver) {
		
		List<AbstractModule> environments = new ArrayList<AbstractModule>();
		
		String environment = nameResolver.getEnvironmentProperty();
		
		for (AbstractModule module : getEnvironments()) {
			Environment annotation = module.getClass().getAnnotation(Environment.class);
			if (isNotNull(annotation) && match(environment, annotation)) {
				environments.add(module);
			}			
		}
		
		return environments;
		
	}
	
	private boolean match(String environment, Environment annotation) {
		return annotation.value().equalsIgnoreCase(environment);
	}
	
	private boolean isNotNull(Environment annotation) {
		return annotation != null && annotation.value() != null;
	}
	
	/**
	 * asd.
	 * @return foo.
	 */
	protected List<AbstractModule> getEnvironments() {
		return Arrays.asList(new TelephonyCoreProductionModule(), new TelephonyCoreTestModule());
	}

}
