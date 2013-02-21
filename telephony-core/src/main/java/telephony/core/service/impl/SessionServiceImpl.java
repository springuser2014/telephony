package telephony.core.service.impl;

import java.util.Date;

import telephony.core.dao.interfaces.UsersDao;
import telephony.core.entity.User;
import telephony.core.service.bean.Session;
import telephony.core.service.interfaces.SessionService;
import telephony.core.util.StringGenerator;

import com.google.inject.Inject;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class SessionServiceImpl
    extends AbstractBasicService implements SessionService {

    /**
     * asd.
     */
    @Inject
    private UsersDao usersDao;

    /**
     * asd.
     */
    @Inject
    private StringGenerator generator;

    /**
     * asd.
     * @param username asd.
     * @param password asd.
     * @return asd.
     */
    @Override
    public final Session init(
        final String username, final String password) {

        User u;

        try {

        getEntityManager().getTransaction().begin();

        u = usersDao.findByNameAndPassword(username, password);

        String sessionId = generator.nextSessionId();

        u.setSessionId(sessionId);
        u.setSessionValidity(new Date());

        usersDao.saveOrUpdate(u);

        getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            return null;
        }

        Session session = new Session(u.getEmail(), u.getSessionId());
        return session;
    }

    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     * @return asd.
     */
    @Override
    public Session refresh(final String username, final String sessionId) {
        User u;

        try {
        getEntityManager().getTransaction().begin();

        u = usersDao.findByNameAndSessionId(username, sessionId);

        Date expirationDate =
             new Date(u.getSessionValidity().getTime() + 30 * 60 * 1000);
        Date today = new Date();

        if (expirationDate.after(today)) {

            u.setSessionValidity(today);
            usersDao.saveOrUpdate(u);
        }

        getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            return null;
        }

        Session session = new Session(u.getEmail(), u.getSessionId());
        return session;
    }

    /**
     * asd.
     * @param username asd.
     * @param sessionId asd.
     * @return asd.
     */
    @Override
    public final boolean destroy(
        final String username, final String sessionId) {

        User u;
        try {
            getEntityManager().getTransaction().begin();

            u = usersDao.findByNameAndSessionId(username, sessionId);

            Date expiredDate = new Date(1000);
            u.setSessionValidity(expiredDate);
            usersDao.saveOrUpdate(u);

            getEntityManager().getTransaction().commit();

        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
