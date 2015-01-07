package telephony.core.service.dto.request;

import telephony.core.service.dto.AnonymousComplaintCommentDto;
import telephony.core.service.dto.BaseComplaintCommentDto;

public class AnonymousComplaintCommentRequest {

    AnonymousComplaintCommentDto complaintComment;

    public AnonymousComplaintCommentDto getComplaintComment() {
        return complaintComment;
    }

    public void setComplaintComment(AnonymousComplaintCommentDto complaintComment) {
        this.complaintComment = complaintComment;
    }
}
