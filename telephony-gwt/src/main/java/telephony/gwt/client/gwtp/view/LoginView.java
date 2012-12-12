package telephony.gwt.client.gwtp.view;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import telephony.gwt.client.gwtp.presenter.LoginPresenter;
import telephony.gwt.client.gwtp.uihandlers.LoginHandlers;
import telephony.gwt.client.ui.widget.form.LoginForm;


public class LoginView extends ViewWithUiHandlers<LoginHandlers> implements LoginPresenter.LoginView {

    private LoginForm loginForm;
    private IButton sendButton;

    private VLayout layout;


    @Inject
    public LoginView() {

        this.layout = new VLayout();
//        this.layout.setShowEdges(true);

        this.layout.setWidth100();
        this.layout.setHeight100();
        this.layout.setLayoutMargin(0);

        this.layout.setMargin(0);
        this.layout.setPadding(0);

        this.loginForm = new LoginForm();
        this.loginForm.setWidth100();
        this.loginForm.setHeight100();
//        this.loginForm.setShowEdges(true);

        this.loginForm.setLayoutAlign(VerticalAlignment.CENTER);
        this.loginForm.setLayoutAlign(Alignment.CENTER);

        this.layout.addMember(loginForm);

        this.sendButton = new IButton("Wyślij");

        this.sendButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                getUiHandlers().onOkButtonClicked();
            }
        });

        this.layout.addMember(sendButton);


    }


    @Override
    public Widget asWidget() {
        return layout;
    }

    public String getPassword() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getLogin() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void resetAndFocus() {

    }

    public void setError(String error) {
        //To change body of implemented methods use File | Settings | File Templates.
    }


}
