package war.server.guice.module;

import com.google.inject.servlet.ServletModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import war.client.service.*;
import war.server.core.configuration.Constant;
import war.server.service.*;


/**
 * Configuration of web applactions specifis modules : GuicePersist, GuiceServlet
 */
public class TelephonyServletModule extends ServletModule {

    private Logger logger = LoggerFactory.getLogger(getClass());

    protected void configureServlets() {

        logger.debug("TelephonyServletModule initialization");

        bindJpa();
        bindServlets();
        bindAuth();

        logger.debug("TelephonyServletModule is destroyed");
    }

    private void bindAuth() {

//        filter("/*").through(GuiceShiroFilter.class);
    }

    private void bindServlets() {

        logger.debug("TelephonyServletModule starts configuring RPC services");

        serve(ProductRPCService.FULL_SERVICE_PATH).with(ProductRPCServiceImpl.class);
        bind(ProductRPCService.class).to(ProductRPCServiceImpl.class);

        serve(StoreRPCService.FULL_SERVICE_PATH).with(StoreRPCServiceImpl.class);
        bind(StoreRPCService.class).to(StoreRPCServiceImpl.class);

        serve(DeliveryRPCService.FULL_SERVICE_PATH).with(DeliveryRPCServiceImpl.class);
        bind(DeliveryRPCService.class).to(DeliveryRPCServiceImpl.class);

        serve(UserRPCService.FULL_SERVICE_PATH).with(UserRPCServiceImpl.class);
        bind(UserRPCService.class).to(UserRPCServiceImpl.class);

        serve(SaleRPCService.FULL_SERVICE_PATH).with(SaleRPCServiceImpl.class);
        bind(SaleRPCService.class).to(SaleRPCServiceImpl.class);

        logger.debug("TelephonyServletModule ends configuring servlets");
    }

    private void bindJpa() {
        logger.debug("TelephonyServletModule starts configuring JPA module");

        install(new JPAModule(Constant.PERSISTENCE_UNIT_NAME));

        logger.debug("TelephonyServletModule ends configuring servlets");
    }
}
