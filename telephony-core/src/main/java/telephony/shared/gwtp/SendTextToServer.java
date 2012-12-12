package telephony.shared.gwtp;


import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * @todo Remove after migration to REST services
 */
public class SendTextToServer extends UnsecuredActionImpl<SendTextToServerResult> {

    private static final long serialVersionUID = 4621412923270714515L;

    private String textToServer;

    public SendTextToServer(final String textToServer) {
        this.textToServer = textToServer;
    }

    @SuppressWarnings("unused")
    private SendTextToServer() {
    }

    public String getTextToServer() {
        return textToServer;
    }

}
