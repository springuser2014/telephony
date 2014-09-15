// CHECKSTYLE:OFF
/**
 * Source code generated by Fluent Builders Generator
 * Do not modify this file
 * See generator home page at: http://code.google.com/p/fluent-builders-generator-eclipse-plugin/
 */

package telephony.core.query.filter;

public class SaleFilterCriteriaBuilder extends
		SaleFilterCriteriaBuilderBase<SaleFilterCriteriaBuilder> {
	public static SaleFilterCriteriaBuilder saleFilterCriteria() {
		return new SaleFilterCriteriaBuilder();
	}

	public SaleFilterCriteriaBuilder() {
		super(new SaleFilterCriteria());
	}

	public SaleFilterCriteria build() {
		return getInstance();
	}
}

class SaleFilterCriteriaBuilderBase<GeneratorT extends SaleFilterCriteriaBuilderBase<GeneratorT>> {
	private SaleFilterCriteria instance;

	protected SaleFilterCriteriaBuilderBase(SaleFilterCriteria aInstance) {
		instance = aInstance;
	}

	protected SaleFilterCriteria getInstance() {
		return instance;
	}
}
