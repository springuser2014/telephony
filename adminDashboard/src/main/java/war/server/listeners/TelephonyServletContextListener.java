package war.server.listeners;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import war.server.guice.TelephonyServerModule;
import war.server.guice.TelephonyServletModule;


public class TelephonyServletContextListener extends GuiceServletContextListener {

//    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new TelephonyServletModule(),
                new TelephonyServerModule()
        );
    }

//    @Override
//    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//        Logger logger = Logger.getLogger("CleanupContextListener");
//        Enumeration<Driver> drivers = DriverManager.getDrivers();
//        while (drivers.hasMoreElements()) {
//            Driver driver = drivers.nextElement();
//            ClassLoader driverclassLoader = driver.getClass().getClassLoader();
//            ClassLoader thisClassLoader = this.getClass().getClassLoader();
//            if (driverclassLoader != null && thisClassLoader != null && driverclassLoader.equals(thisClassLoader)) {
//                try {
//                    logger.warn("Deregistering: " + driver);
//                    DriverManager.deregisterDriver(driver);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

//    @Override
//    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        super.contextInitialized(servletContextEvent);
//        logger.debug("context initialized.");
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//        super.contextDestroyed(servletContextEvent);
//        logger.debug("context destroyed.");
//    }
}
