package telephony.core.query.filter;

/**
 * asd.
 */
public class RoleFilterCriteria
extends AbstractFilterCriteria<RoleFilterCriteria> {
	
	private String label;
	
	/**
	 * asd.
	 * @return a.
	 */
	public static RoleFilterCriteria create() {
		return new RoleFilterCriteria();
	}

	/**
	 * asd.
	 * @return a.
	 */
	public String label() {
		return label;
	}

	/**
	 * asd.
	 * @param label asd.
	 * @return asd. 
	 */
	public RoleFilterCriteria label(String label) {
		this.label = label;
		return this;
	}
}
