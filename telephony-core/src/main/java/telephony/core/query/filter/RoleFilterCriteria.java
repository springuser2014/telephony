package telephony.core.query.filter;

public class RoleFilterCriteria
extends AbstractFilterCriteria<RoleFilterCriteria> {

	private String labelLike;
	private String label;
	
	public String getLabel() {
		return label;
	}

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
