package telephony.core.service.impl;

import com.google.inject.Inject;
import telephony.core.dao.interfaces.UsersDao;
import telephony.core.entity.User;
import telephony.core.service.Session;
import telephony.core.service.interfaces.SessionService;

import java.util.Date;

public class SessionServiceImpl extends AbstractBasicService implements SessionService {

    @Inject
    private UsersDao usersDao;

    @Override
    public Session init(String username, String password) {

        User u;

        try {

//        getEntityManager().getTransaction().begin();

            u = usersDao.findByNameAndPassword(username, password);

//            String sessionId = DigestUtils.md5Hex(username.toString() + new Date().toString());

            String sessionId = username.toString() + new Date().toString();

//        u.setSessionId(sessionId);
//        u.setSessionValidity(new Date());
//
//        usersDao.saveOrUpdate(u);

            usersDao.updateSession(u.getId(), sessionId, new Date());

//        getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            return null;
        }

        Session session = new Session(u.getEmail(), u.getSessionId());
        return session;
    }

    @Override
    public Session refresh(String username, String sessionId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean destroy(String username, String sessionId) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
