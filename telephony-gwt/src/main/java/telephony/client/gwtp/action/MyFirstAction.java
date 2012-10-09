package telephony.client.gwtp.action;

import com.gwtplatform.dispatch.shared.Action;
import telephony.shared.gwtp.result.MyFirstResult;
import telephony.client.service.SecurityRPCService;


public class MyFirstAction implements Action<MyFirstResult> {

    public MyFirstAction() {}

    public String getServiceName() {
        return SecurityRPCService.FULL_SERVICE_URL;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isSecured() {
        return false;
    }
}
