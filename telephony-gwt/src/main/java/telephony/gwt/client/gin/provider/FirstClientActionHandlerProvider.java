package telephony.gwt.client.gin.provider;


import com.google.inject.Provider;
import telephony.gwt.client.gwtp.handler.FirstClientActionHandler;


/**
 * @todo remove in near future
 */
public class FirstClientActionHandlerProvider implements Provider<FirstClientActionHandler> {
    public FirstClientActionHandler get() {
        return new FirstClientActionHandler();
    }
}
