// CHECKSTYLE:OFF
/**
 * Source code generated by Fluent Builders Generator
 * Do not modify this file
 * See generator home page at: http://code.google.com/p/fluent-builders-generator-eclipse-plugin/
 */

package telephony.core.query.filter;

public class StoreFilterCriteriaBuilder extends
		StoreFilterCriteriaBuilderBase<StoreFilterCriteriaBuilder> {
	public static StoreFilterCriteriaBuilder storeFilterCriteria() {
		return new StoreFilterCriteriaBuilder();
	}

	public StoreFilterCriteriaBuilder() {
		super(new StoreFilterCriteria());
	}

	public StoreFilterCriteria build() {
		return getInstance();
	}
}

class StoreFilterCriteriaBuilderBase<GeneratorT extends StoreFilterCriteriaBuilderBase<GeneratorT>> {
	private StoreFilterCriteria instance;

	protected StoreFilterCriteriaBuilderBase(StoreFilterCriteria aInstance) {
		instance = aInstance;
	}

	protected StoreFilterCriteria getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public GeneratorT withLabel(String aValue) {
		instance.setLabel(aValue);

		return (GeneratorT) this;
	}

	@SuppressWarnings("unchecked")
	public GeneratorT withMinNumberOfProducts(Integer aValue) {
		instance.setMinNumberOfProducts(aValue);

		return (GeneratorT) this;
	}

	@SuppressWarnings("unchecked")
	public GeneratorT withMaxNumberOfProducts(Integer aValue) {
		instance.setMaxNumberOfProducts(aValue);

		return (GeneratorT) this;
	}
}
