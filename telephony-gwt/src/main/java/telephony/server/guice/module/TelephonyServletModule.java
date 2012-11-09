package telephony.server.guice.module;


import com.google.inject.servlet.ServletModule;
import com.gwtplatform.dispatch.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.shared.ActionImpl;
import com.gwtplatform.dispatch.shared.DispatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import telephony.client.service.InformationRPCService;
import telephony.server.TelephonyRestletServlet;
import telephony.server.core.Application;
import telephony.server.core.configuration.Constant;
import telephony.server.service.InformationRPCServiceImpl;

import java.util.HashMap;
import java.util.Map;


/**
 * Configuration of web applactions specifis modules : GuicePersist, GuiceServlet
 */
public class TelephonyServletModule extends ServletModule {

    private Logger logger = LoggerFactory.getLogger(getClass());

    protected void configureServlets() {

        logger.debug("TelephonyServletModule initialization");

        bind(DispatchService.class).to(DispatchServiceImpl.class);

        bindJpa();
        bindServlets();
        bindAuth();

        logger.debug("TelephonyServletModule is destroyed");
    }

    private void bindAuth() {

//        filter("/*").through(GuiceShiroFilter.class);
    }

    private void bindServlets() {

        // gwtp starts
        serve("/mygwtapp/" + ActionImpl.DEFAULT_SERVICE_NAME).with(DispatchServiceImpl.class);

        // gwtp ends

//        logger.debug("TelephonyServletModule starts configuring RPC services");

//        serve(ProductRPCService.FULL_SERVICE_PATH).with(ProductRPCServiceImpl.class);
//        bind(ProductRPCService.class).to(ProductRPCServiceImpl.class);
//
//        serve(StoreRPCService.FULL_SERVICE_PATH).with(StoreRPCServiceImpl.class);
//        bind(StoreRPCService.class).to(StoreRPCServiceImpl.class);
//
//        serve(DeliveryRPCService.FULL_SERVICE_PATH).with(DeliveryRPCServiceImpl.class);
//        bind(DeliveryRPCService.class).to(DeliveryRPCServiceImpl.class);
//
//        serve(UserRPCService.FULL_SERVICE_PATH).with(UserRPCServiceImpl.class);
//        bind(UserRPCService.class).to(UserRPCServiceImpl.class);
//
//        serve(SaleRPCService.FULL_SERVICE_PATH).with(SaleRPCServiceImpl.class);
//        bind(SaleRPCService.class).to(SaleRPCServiceImpl.class);
//
//        serve(InformationRPCService.FULL_SERVICE_PATH).with(InformationRPCServiceImpl.class);
        bind(InformationRPCService.class).to(InformationRPCServiceImpl.class);

        logger.debug("TelephonyServletModule starts configuring Restlet services");

        bind(org.restlet.Application.class).to(Application.class);

        Map<String, String> restparams = bindRestletParams();
        serve("/rest/*").with(TelephonyRestletServlet.class, restparams);

        logger.debug("TelephonyServletModule ends configuring servlets");
    }

    private Map<String, String> bindRestletParams() {
        Map<String, String> options = new HashMap();
        options.put("org.restlet.application", Application.class.toString());

        return options;
    }

    private void bindJpa() {
        logger.debug("TelephonyServletModule starts configuring JPA module");

        install(new JPAModule(Constant.PERSISTENCE_UNIT_NAME));

        logger.debug("TelephonyServletModule ends configuring servlets");
    }
}
