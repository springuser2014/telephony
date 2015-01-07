package telephony.core.service.dto.request;

import telephony.core.service.dto.BaseComplaintCommentDto;
import telephony.core.service.dto.ComplaintCommentDto;
import telephony.core.service.dto.SessionDto;

public class ComplaintCommentRequest extends AuthRequest {

    ComplaintCommentDto complaintComment;

    public ComplaintCommentRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public ComplaintCommentDto getComplaintComment() {
        return complaintComment;
    }

    public void setComplaintComment(ComplaintCommentDto complaintComment) {
        this.complaintComment = complaintComment;
    }
}
