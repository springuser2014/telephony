package telephony.client.gin.provider;


import com.google.inject.Provider;
import telephony.client.gwtp.handler.FirstClientActionHandler;


public class FirstClientActionHandlerProvider  implements Provider<FirstClientActionHandler> {
    public FirstClientActionHandler get() {
        return new FirstClientActionHandler();
    }
}
