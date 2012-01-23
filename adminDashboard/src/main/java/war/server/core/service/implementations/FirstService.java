package war.server.core.service.implementations;


import com.google.inject.Inject;
import war.server.core.entity.User;
import war.server.core.service.interfaces.MyFirstService;

import javax.persistence.EntityManager;
import java.util.List;

public class FirstService implements MyFirstService {

    @Inject
    private  EntityManager em;

    @Override
    public List<User> getAllUsers() {



        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
