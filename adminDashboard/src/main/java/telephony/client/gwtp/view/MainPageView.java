package telephony.client.gwtp.view;

import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import telephony.client.gwtp.presenter.MainPagePresenter;

public class MainPageView extends ViewImpl
        implements MainPagePresenter.MyView {

    private static String html =
            "<h1>Web Application Starter Project</h1>\n" +
                    "<table align=\"center\">\n" +
                    "  <tr>\n" +
                    "    <td colspan=\"2\" style=\"font-weight:bold;\">Please enter your name:</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td id=\"nameFieldContainer\"></td>\n" +
                    "    <td id=\"sendButtonContainer\"></td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td colspan=\"2\" style=\"color:red;\" id=\"errorLabelContainer\"></td>\n" +
                    "  </tr>\n" +
                    "</table>\n";

    HTMLPanel panel = new HTMLPanel(html);
    
    private final TextBox nameField;
    private final Button sendButton;
    private final Label errorLabel;

    @Inject
    public MainPageView() {
        nameField = new TextBox();
        sendButton = new Button("Send");
        errorLabel = new Label();
        
        nameField.setText("GWTP User");
        panel.add(nameField, "nameFieldContainer");
        panel.add(sendButton, "sendButtonContainer");
        panel.add(errorLabel, "errorLabelContainer");
    }

    @Override
    public Widget asWidget() {
        return panel;
    }
    
    public String getName() {
        return nameField.getText();
    }
    
    public Button getSendButton() {
        return sendButton;
    }
    
    public void resetAndFocus() {
        nameField.setFocus(true);
        nameField.selectAll();
    }
    
    public void setError(String errorText) {
        errorLabel.setText(errorText);
    }

}