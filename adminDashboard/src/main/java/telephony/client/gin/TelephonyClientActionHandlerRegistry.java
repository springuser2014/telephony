package telephony.client.gin;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.actionhandler.DefaultClientActionHandlerRegistry;
import telephony.client.gwtp.action.MyFirstAction;
import telephony.client.gwtp.handler.FirstClientActionHandler;


public class TelephonyClientActionHandlerRegistry extends DefaultClientActionHandlerRegistry {

    @Inject
    public TelephonyClientActionHandlerRegistry(Provider<FirstClientActionHandler> handler) {
        register(MyFirstAction.class, handler);
    }

}
