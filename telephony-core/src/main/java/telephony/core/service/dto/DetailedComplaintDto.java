package telephony.core.service.dto;

import telephony.core.service.dto.ComplaintCommentDto;
import telephony.core.service.dto.ComplaintEditDto;

import java.util.ArrayList;
import java.util.List;

public abstract class DetailedComplaintDto extends ComplaintEditDto {

    List<ComplaintCommentDto> comments;

    public DetailedComplaintDto() {
        comments = new ArrayList<ComplaintCommentDto>();
    }

    public List<ComplaintCommentDto> getComments() {
        return comments;
    }

    public void setComments(List<ComplaintCommentDto> comments) {
        this.comments = comments;
    }

    public void addComment(ComplaintCommentDto comment) {

        if (!comments.contains(comment)) {
            comments.add(comment);
        }
    }

    public void removeComment(ComplaintCommentDto comment) {

        if (comments.contains(comment)) {
            comments.remove(comment);
        }
    }
}
