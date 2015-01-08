package telephony.core.service.dto;

public class DetailedComplaintCommentDto extends ComplaintCommentDto {

    Long commentId;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
}
