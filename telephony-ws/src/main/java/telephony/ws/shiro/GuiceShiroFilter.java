package telephony.ws.shiro;

import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * asd.
 */
@Singleton
public class GuiceShiroFilter extends AbstractShiroFilter {

    /**
     * asd.
     * @param webSecurityManager asd.
     */
    @Inject
    public GuiceShiroFilter(WebSecurityManager webSecurityManager) {
        setSecurityManager(webSecurityManager);
    }
}
