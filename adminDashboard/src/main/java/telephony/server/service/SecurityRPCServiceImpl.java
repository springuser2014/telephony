package telephony.server.service;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.sf.gilead.core.PersistentBeanManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.client.service.SecurityRPCService;
import telephony.server.gilead.GuicePersistentRemoteServiceServlet;
import telephony.shared.gwtp.result.LoginResult;

@SuppressWarnings("serial")
@Singleton
public class SecurityRPCServiceImpl extends GuicePersistentRemoteServiceServlet implements SecurityRPCService {

    private static Logger logger = LoggerFactory.getLogger(SecurityRPCServiceImpl.class);

    @Inject
    public SecurityRPCServiceImpl(PersistentBeanManager beanManager) {
        setBeanManager(beanManager);
    }

    public LoginResult login(String username, String password, boolean rememberMe) {
        logger.debug("LOGIN (username: " + username + " password: " + password + ")");

        Subject subject = SecurityUtils.getSubject();
        LoginResult result = new LoginResult();
        result.setSuccess(false);
        result.setReferrerUrl(getReferrerUrl());

        if (subject.isAuthenticated()) {
            result.setSuccess(true);
            return result;
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(rememberMe);
        doLogin(subject, token, result);

        return result;
    }

    public void logout() {
        logger.debug("LOGOUT");
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }

    protected void doLogin(Subject subject, AuthenticationToken token, LoginResult result) {
        try {
            subject.login(token);
            result.setSuccess(true);
        } catch (UnknownAccountException uae) {
            // username wasn't in the system, show them an error message?
            result.setErrorMessage("Konto nie istnieje");
        } catch (IncorrectCredentialsException ice) {
            // password didn't match, try again?
            result.setErrorMessage("Wprowadzona nazwa użytkownika lub hasło jest nieprawidłowe.");
        } catch (LockedAccountException lae) {
            // account for that username is locked - can't login. Show them a message?
            result.setErrorMessage("Konto jest zablokowane");
        } catch (AuthenticationException ae) {
            // unexpected condition - error?

            logger.debug("AuthenticationException : " + ae.getStackTrace() );
            result.setErrorMessage("Wystąpił nieznany błąd");
        }
    }

    protected String getReferrerUrl() {
        SavedRequest sr = WebUtils.getSavedRequest(getThreadLocalRequest());
        if (sr != null) {
            logger.debug("Request Url: " + sr.getRequestUrl());
            return sr.getRequestUrl();
        }

        return null;
    }
}
