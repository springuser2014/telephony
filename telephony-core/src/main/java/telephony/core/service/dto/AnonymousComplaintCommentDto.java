package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AnonymousComplaintCommentDto extends BaseComplaintCommentDto {

    String hashUnique;

    public String getHashUnique() {
        return hashUnique;
    }

    public void setHashUnique(String hashUnique) {
        this.hashUnique = hashUnique;
    }
}
