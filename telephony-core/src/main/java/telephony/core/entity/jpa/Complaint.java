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
	
	@Column(name = "unique_hash")
	private String uniqueHash;
	
	@OneToMany(mappedBy = "complaint", fetch = FetchType.EAGER)
	private Set<ComplaintComment> comments;

	public Set<ComplaintComment> getComments() {
		return comments;
	}

	public void setComments(Set<ComplaintComment> comments) {
		this.comments = comments;
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

	public void addComment(ComplaintComment comment) {
		
		if (comments.contains(comment)) {
			return;
		}

		comments.add(comment);
		comment.setComplaint(this);
	}

	public void removeComment(ComplaintComment comment) {

		if (comments.contains(comment)) {
			return;
		}

		comments.remove(comment);
		comment.setComplaint(null);
	}
}