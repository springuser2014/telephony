package telephony.server.guice.module;

import com.gwtplatform.dispatch.server.guice.HandlerModule;
import telephony.server.gwtp.SendTextToServerHandler;
import telephony.shared.gwtp.SendTextToServer;


public class HandlersModule extends HandlerModule {

    @Override
    protected void configureHandlers() {
        bindHandler(SendTextToServer.class, SendTextToServerHandler.class);
    }
}