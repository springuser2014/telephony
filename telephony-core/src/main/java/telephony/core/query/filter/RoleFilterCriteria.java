package telephony.core.query.filter;

/**
 * asd.
 */
public class RoleFilterCriteria
	extends AbstractFilterCriteria<RoleFilterCriteria> {

	private String labelLike;
	private String label;
	
	/**
	 * asd.
	 * @return a.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * asd.
	 * @param label asd.
	 * @return asd. 
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabelLike() {
		return labelLike;
	}

	public void setLabelLike(String labelLike) {
		this.labelLike = labelLike;
	}
}
