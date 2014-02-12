package telephony.core.entity.jpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 * asd.
 *
 */

@Entity
@DiscriminatorValue("P")
public class ProductComplaint extends Complaint {

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}

}
