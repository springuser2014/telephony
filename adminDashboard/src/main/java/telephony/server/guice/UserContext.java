package telephony.server.guice;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Singleton
public class UserContext {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    public UserContext(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        logger.debug("UserContext constructor..");
    }
}
