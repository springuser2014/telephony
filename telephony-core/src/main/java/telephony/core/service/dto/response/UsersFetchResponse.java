package telephony.core.service.dto.response;

import telephony.core.service.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UsersFetchResponse extends BasicResponse {

    List<UserDto> users;

    public UsersFetchResponse() {
        this.users = new ArrayList<UserDto>();
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
