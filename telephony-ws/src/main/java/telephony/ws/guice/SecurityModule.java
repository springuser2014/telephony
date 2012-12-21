package telephony.ws.guice;


import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import telephony.ws.shiro.realm.TelephonyRealm;
import telephony.ws.shiro.interceptor.TelephonyShiroMethodInterceptor;

public class SecurityModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(Realm.class).to(TelephonyRealm.class);
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(RequiresRoles.class),
                new TelephonyShiroMethodInterceptor());
    }

    @Provides
    @Singleton
    WebSecurityManager provideSecurityManager(Realm realm, SessionManager sessionManager, RememberMeManager rememberMeManager) {
        DefaultWebSecurityManager result = new DefaultWebSecurityManager(realm);
        result.setSessionManager(sessionManager);
        result.setRememberMeManager(rememberMeManager);
        return result;
    }

    @Provides
    @Singleton
    SessionManager provideSessionManager() {
        return new ServletContainerSessionManager();
    }

    @Provides
    @Singleton
    RememberMeManager provideRememberMeManager() {
        return new CookieRememberMeManager();
    }
}