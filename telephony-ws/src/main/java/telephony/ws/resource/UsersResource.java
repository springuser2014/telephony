package telephony.ws.resource;

import java.util.List;

import org.restlet.resource.Get;

import telephony.core.entity.jpa.User;


/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public interface UsersResource {

    /**
     * asd.
     * @return asd.
     */
    @Get("json")
    List<User> retrieve();
}
