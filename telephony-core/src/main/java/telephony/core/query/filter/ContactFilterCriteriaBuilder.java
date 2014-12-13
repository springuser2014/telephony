// CHECKSTYLE:OFF
/**
 * Source code generated by Fluent Builders Generator
 * Do not modify this file
 * See generator home page at: http://code.google.com/p/fluent-builders-generator-eclipse-plugin/
 */

package telephony.core.query.filter;

public class ContactFilterCriteriaBuilder extends
		ContactFilterCriteriaBuilderBase<ContactFilterCriteriaBuilder> {
	public static ContactFilterCriteriaBuilder contactFilterCriteria() {
		return new ContactFilterCriteriaBuilder();
	}

	public ContactFilterCriteriaBuilder() {
		super(new ContactFilterCriteria());
	}

	public ContactFilterCriteria build() {
		return getInstance();
	}
}

class ContactFilterCriteriaBuilderBase<GeneratorT extends ContactFilterCriteriaBuilderBase<GeneratorT>> {
	private ContactFilterCriteria instance;

	protected ContactFilterCriteriaBuilderBase(ContactFilterCriteria aInstance) {
		instance = aInstance;
	}

	protected ContactFilterCriteria getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	public GeneratorT withId(Long aValue) {
		instance.setId(aValue);

		return (GeneratorT) this;
	}

	@SuppressWarnings("unchecked")
	public GeneratorT withLabel(String aValue) {
		instance.setLabel(aValue);

		return (GeneratorT) this;
	}

	@SuppressWarnings("unchecked")
	public GeneratorT withDetails(String aValue) {
		instance.setDetails(aValue);

		return (GeneratorT) this;
	}

	@SuppressWarnings("unchecked")
	public GeneratorT withPhonenumber(String aValue) {
		instance.setPhonenumber(aValue);

		return (GeneratorT) this;
	}

	@SuppressWarnings("unchecked")
	public GeneratorT withEmail(String aValue) {
		instance.setEmail(aValue);

		return (GeneratorT) this;
	}

	@SuppressWarnings("unchecked")
	public GeneratorT withPage(Integer aValue) {
		instance.setPage(aValue);

		return (GeneratorT) this;
	}

	@SuppressWarnings("unchecked")
	public GeneratorT withPerPage(Integer aValue) {
		instance.setPerPage(aValue);

		return (GeneratorT) this;
	}


}