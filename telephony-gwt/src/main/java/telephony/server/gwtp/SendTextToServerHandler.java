package telephony.server.gwtp;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;
import telephony.shared.gwtp.FieldVerifier;
import telephony.shared.gwtp.SendTextToServer;
import telephony.shared.gwtp.SendTextToServerResult;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


public class SendTextToServerHandler implements ActionHandler<SendTextToServer, SendTextToServerResult> {

    private Provider<HttpServletRequest> requestProvider;
    private ServletContext servletContext;

    @Inject
    SendTextToServerHandler(ServletContext servletContext1,
                            Provider<HttpServletRequest> requestProvider1) {
        this.servletContext = servletContext1;
        this.requestProvider = requestProvider1;
    }

    public SendTextToServerResult execute(SendTextToServer action, ExecutionContext context)
            throws ActionException {
        String input = action.getTextToServer();

        if (!FieldVerifier.isValidUserName(input)) {
            throw new ActionException("Name must be at least 4 characters long");
        }

        String serverInfo = servletContext.getServerInfo();
        String userAgent = requestProvider.get().getHeader("User-Agent");

        return new SendTextToServerResult("Hello, " + input
                + "!<br><br>I am running " + serverInfo
                + ".<br><br>It looks like you are using :<br>" + userAgent);
    }

    public Class<SendTextToServer> getActionType() {
        return SendTextToServer.class;
    }

    public void undo(SendTextToServer action, SendTextToServerResult result, ExecutionContext context)
            throws ActionException {

    }

}
