package telephony.client.gwtp.view;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.layout.VLayout;
import telephony.client.gwtp.presenter.LoginPresenter;
import telephony.client.gwtp.uihandlers.LoginHandlers;
import telephony.client.ui.widget.form.LoginForm;


public class LoginView extends ViewWithUiHandlers<LoginHandlers> implements LoginPresenter.LoginView {


    private LoginForm loginForm;

    private VLayout layout;


    @Inject
    public LoginView() {
        this.layout = new VLayout();
        this.layout.setShowEdges(true);


        this.loginForm = new LoginForm();
        this.loginForm.setWidth("200px");
        this.loginForm.setHeight("200px");
        this.loginForm.setShowEdges(true);

        this.layout.addMember(loginForm);
        this.layout.setWidth100();
        this.layout.setHeight100();
        this.layout.setLayoutMargin(0);
        this.layout.setLayoutAlign(VerticalAlignment.CENTER);
        this.layout.setLayoutAlign(Alignment.CENTER);
        this.layout.setMargin(0);
        this.layout.setPadding(0);
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
