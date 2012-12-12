package telephony.gwt.client.gin;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.actionhandler.DefaultClientActionHandlerRegistry;
import telephony.gwt.client.gwtp.action.MyFirstAction;
import telephony.gwt.client.gwtp.handler.FirstClientActionHandler;


public class TelephonyClientActionHandlerRegistry extends DefaultClientActionHandlerRegistry {

    @Inject
    public TelephonyClientActionHandlerRegistry(Provider<FirstClientActionHandler> handler) {
        register(MyFirstAction.class, handler);
    }

}
