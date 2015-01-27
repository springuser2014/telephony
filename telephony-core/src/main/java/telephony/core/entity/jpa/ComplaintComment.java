package telephony.core.entity.jpa;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "complaint_comments")
public class ComplaintComment extends BaseEntity {

	@Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(
		strategy = GenerationType.SEQUENCE, 
		generator = "complaint_comments_seq")
    @SequenceGenerator(
		name = "complaint_comments_seq", 
		sequenceName = "complaint_comments_seq", 
		allocationSize = 1)
    private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "complaint_id", nullable = false)
    private Complaint complaint;

	@Column(name = "author", nullable = false, length = 50)
    private String author;
	
	@Column(name = "content", nullable = false)
    private String content;
	
	@Column(name = "reported_date", nullable = false)
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

	public Complaint getComplaint() {
		return complaint;
	}
	
	public void setComplaint(Complaint complaint) {

		if (sameAsFormter(complaint)) {
			return;
		}

		Complaint oldComplaint = this.complaint;
		this.complaint = complaint;

		if (oldComplaint != null) {
			oldComplaint.removeComment(this);
		}

		if (complaint != null) {
			complaint.addComment(this);
		}
	}

	private boolean sameAsFormter(Complaint complaint) {
		return this.complaint == null ?
				complaint == null :
					this.complaint.equals(complaint);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}	
}
