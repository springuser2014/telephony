package telephony.core.entity.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TODO : move to src/test/java
 */
@Entity
@Table(name = "test_entity_table")
public class TestEntity  extends BaseEntity {
	
	@Id
    @Column(
		name = "id", 
		updatable = false, 
		nullable = false)
    @GeneratedValue(
		strategy = GenerationType.SEQUENCE, 
		generator = "test_entity_table_seq")
    @SequenceGenerator(
		name = "test_entity_table_seq", 
		sequenceName = "test_entity_table_seq", 
		allocationSize = 1)
	private Long id; 
	
    @Column(name = "label", nullable = false, length = 100)
	private String label;	

    @Column(name = "reported_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportedDate;
	
	@Override
	public Long getId() {
		return this.id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * asd.
	 * @param label a.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public Date getReportedDate() {
		return reportedDate;
	}

	/**
	 * asd.
	 * @param reportedDate a.
	 */
	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}
}
