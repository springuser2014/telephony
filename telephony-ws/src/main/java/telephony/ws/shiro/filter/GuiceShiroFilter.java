package telephony.ws.shiro.filter;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;

@Singleton
public class GuiceShiroFilter extends AbstractShiroFilter {

    @Inject
    public GuiceShiroFilter(WebSecurityManager webSecurityManager) {
        setSecurityManager(webSecurityManager);
    }
}
