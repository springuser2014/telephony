package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import telephony.core.entity.enumz.ComplaintStatus;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ComplaintDto {

    String description;
    Date reportedDate;
    ComplaintStatus status;
    String title;
    String uniqueHash;

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
