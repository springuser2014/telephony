package telephony.core.service.dto.response;

import telephony.core.service.dto.UserFetchDto;

import java.util.ArrayList;
import java.util.List;

public class UsersFetchResponse extends BasicResponse {

    List<UserFetchDto> users;

    public UsersFetchResponse() {
        this.users = new ArrayList<UserFetchDto>();
    }

    public List<UserFetchDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserFetchDto> users) {
        this.users = users;
    }
}
