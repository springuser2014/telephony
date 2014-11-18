package telephony.core.entity.jpa;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import telephony.core.entity.enumz.ComplaintStatus;

/**
 * asd.
 */
@Entity
@Inheritance
@DiscriminatorColumn(name = "complaint_type")
@Table(name = "complaints")
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
	
	@Column(name = "item_id")
	private String itemId;
	
	@Column(name = "unique_hash")
	private String uniqueHash;
	
	@OneToMany(mappedBy = "complaint", fetch = FetchType.EAGER)
	private Set<ComplaintComment> comments;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_id", nullable = false)
	private Contact contact;

	/**
	 * asd.
	 * @return a.
	 */
	public Set<ComplaintComment> getComments() {
		return comments;
	}

	/**
	 * asd.
	 * @param comments a.
	 */
	public void setComments(Set<ComplaintComment> comments) {
		this.comments = comments;
	}

	/**
	 * asd.
	 * @param contact asd.
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	/**
	 * asd.
	 * @return a.
	 */
	public Contact getContact() {
		return this.contact;
	}

	
	/* TODO : implement later
	@ManyToMany(mappedBy = "complaints", fetch = FetchType.LAZY)
	private Set<File> files;
	*/
	
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
	 * @return asd.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * asd.
	 * @param description asd.
	 */
	public void setDescription(String description) {
		this.description = description;
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

	/**
	 * as.
	 * @return a.
	 */
	public ComplaintStatus getStatus() {
		return status;
	}

	/**
	 * ad.
	 * @param status a.
	 */
	public void setStatus(ComplaintStatus status) {
		this.status = status;
	}

	/**
	 * a.
	 * @return a.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * a.
	 * @param title a.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public String getUniqueHash() {
		return uniqueHash;
	}

	/**
	 * asd.
	 * @param uniqueHash a.
	 */
	public void setUniqueHash(String uniqueHash) {
		this.uniqueHash = uniqueHash;
	}

	/**
	 * asd.
	 * @return a.
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * asd.
	 * @param itemId a.
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * asd.
	 * @param complaintComment a.
	 */
	public void addComment(ComplaintComment complaintComment) {
		
		this.comments.add(complaintComment);
	}
}