package telephony.core.service.dto.response;

import telephony.core.service.dto.UserFetchDto;

public class SessionDetailsResponse extends BasicResponse {

    UserFetchDto details;

    public UserFetchDto getDetails() {
        return details;
    }

    public void setDetails(UserFetchDto details) {
        this.details = details;
    }
}
