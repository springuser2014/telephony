package telephony.core.service.dto;

public class AnonymousComplaintCommentDto extends BaseComplaintCommentDto {

    String hashUnique;

    public String getHashUnique() {
        return hashUnique;
    }

    public void setHashUnique(String hashUnique) {
        this.hashUnique = hashUnique;
    }
}
