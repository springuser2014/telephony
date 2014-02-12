package telephony.core.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * asd.
 *
 */
@Entity
@Table(name = "files")
public class File extends BaseEntity {

	@Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "files_seq")
    @SequenceGenerator(
        name = "files_seq",
        sequenceName = "files_seq",
        allocationSize = 1)
    private Long id;
	
	@Column(name = "label", length = 100, nullable = false, unique = true)
	private String label;
	
	@Column(name = "filename", length = 200, nullable = false)
	private String filename;
	 
	@Column(name = "mime_type", length = 100, nullable = false)
	private String mimeType;
	
	@Column(name = "size_in_bytes", nullable = false)
	private Long sizeInBytes;
	
//	@Lob
//	@Column(name = "content", nullable = false)
//	private String content;
	
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
	public String getLabel() {
		return label;
	}

	/**
	 * ads.
	 * @param label asd.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * asd.
	 * @param filename asd.
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * asd.
	 * @return asd.
 	 */
	public String getMimeType() {
		return mimeType;
	}

	/**
	 * asd.
	 * @param mimeType asd.
	 */
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	/**
	 * asd.
	 * @return asd.
	 */
	public Long getSizeInBytes() {
		return sizeInBytes;
	}

	/**
	 * asd.
	 * @param sizeInBytes asd.
	 */
	public void setSizeInBytes(Long sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}

	/**
	 * ads.
	 * @return ads.
	 */
	public String getContent() {
		return null;
//		return content;
	}

	/**
	 * asd.
	 * @param content asd.
	 */
	public void setContent(String content) {
//		this.content = content;
	}
}
