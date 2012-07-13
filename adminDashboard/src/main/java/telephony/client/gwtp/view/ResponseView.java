package telephony.client.gwtp.view;

import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import telephony.client.gwtp.presenter.ResponsePresenter;

public class ResponseView extends ViewImpl implements ResponsePresenter.MyView {

  private static String html = "<h1>Remote Procedure Call</h1>\n"
      + "<table align=\"center\">\n" + "  <tr>\n"
      + "    <td style=\"font-weight:bold;\">Sending name to the server:</td>\n"
      + "  </tr>\n" + "  <tr>\n"
      + "    <td id=\"textToServerContainer\"></td>\n" + "  </tr>\n"
      + "  <tr>\n"
      + "    <td style=\"font-weight:bold;\">Server replies:</td>\n"
      + "  </tr>\n" + "  <tr>\n"
      + "    <td id=\"serverResponseContainer\"></td>\n" + "  </tr>\n"
      + "  <tr>\n" + "    <td id=\"closeButtonContainer\"></td>\n" + "  </tr>\n"
      + "</table>\n";

  HTMLPanel panel = new HTMLPanel(html);

  private final Label textToServerLabel;
  private final HTML serverResponseLabel;
  private final Button closeButton;

  @Inject
  public ResponseView() {
    textToServerLabel = new Label();
    serverResponseLabel = new HTML();
    closeButton = new Button("Close");

    // closeButton.getElement().setId("closeButtonContainer");

    panel.add(textToServerLabel, "textToServerContainer");
    panel.add(serverResponseLabel, "serverResponseContainer");
    panel.add(closeButton, "closeButtonContainer");
  }

  public Widget asWidget() {
    return panel;
  }

  public Button getCloseButton() {
    return closeButton;
  }

  public void setServerResponse(String serverResponse) {
    serverResponseLabel.setHTML(serverResponse);
  }

  public void setTextToServer(String textToServer) {
    textToServerLabel.setText(textToServer);
  }
}
