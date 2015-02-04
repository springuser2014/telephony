package telephony.core.entity.jpa;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import telephony.core.entity.enumz.ComplaintStatus;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "complaint_type")
@Table(name = "complaints")
@MappedSuperclass
public abstract class Complaint extends BaseEntity {
	
	@Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complaints_seq")
    @SequenceGenerator(name = "complaints_seq", sequenceName = "complaints_seq", allocationSize = 1)
	private Long id;
	
	@Column(name = "description")
	private String description;	
	
	@Column(name = "reported_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportedDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private ComplaintStatus status;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "unique_hash")
	private String uniqueHash;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_id", nullable = false)
	private Contact contact;

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@Override
	public Long getId() {
		return this.id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}

	public ComplaintStatus getStatus() {
		return status;
	}

	public void setStatus(ComplaintStatus status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUniqueHash() {
		return uniqueHash;
	}

	public void setUniqueHash(String uniqueHash) {
		this.uniqueHash = uniqueHash;
	}

}