package telephony.ws.shiro.realm;

import com.google.inject.Inject;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.core.MyPrincipal;
import telephony.core.MySecurityManagerService;

import java.util.HashSet;

public class TelephonyRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(TelephonyRealm.class.getName());

    private MySecurityManagerService mySecurityManagerService;

    @Inject
    public TelephonyRealm(MySecurityManagerService mySecurityManagerService) {
        // This is the thing that knows how to find user creds and roles
        this.mySecurityManagerService = mySecurityManagerService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username = (String) principalCollection.getPrimaryPrincipal();

        // Find the thing that stores your user's roles.
        MyPrincipal principal = mySecurityManagerService.findMyPrincipalByUsername(username);
        if (principal == null) {

            logger.debug("Principal not found for authorizing user with username: " + username);

            return null;
        } else {

            logger.debug(String.format("Authoriziong user %s with roles: %s", username, principal.getRoles()));

            SimpleAuthorizationInfo result = new SimpleAuthorizationInfo(principal.getRoles());
            return result;
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {

        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        if (usernamePasswordToken.getUsername() == null || usernamePasswordToken.getUsername().isEmpty()) {
            throw new AuthenticationException("Authentication failed");
        }

        // Find the thing that stores your user's credentials.  This may be the same or different than
        // the thing that stores the roles.
        MyPrincipal principal = mySecurityManagerService.findMyPrincipalByUsername(usernamePasswordToken.getUsername());
        if (principal == null) {

            logger.debug("Principal not found for user with username: " + usernamePasswordToken.getUsername());

            return null;
        }

        logger.debug("Principal found for authenticating user with username: " + usernamePasswordToken.getUsername());


        return new SimpleAccount(principal.getUsername(), principal.getPassword(), getName(),
                principal.getRoles(), new HashSet());
    }
}