package telephony.gwt.client.ui.widget.form;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.data.fields.DataSourcePasswordField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import telephony.gwt.client.configuration.CSS;
import telephony.gwt.client.configuration.REGEX;
import telephony.gwt.client.service.SecurityRPCService;
import telephony.gwt.client.service.SecurityRPCServiceAsync;


/**
 * @todo refactor me
 * @todo delete?
 */
public class LoginForm extends DynamicForm {


    SecurityRPCServiceAsync service = GWT.create(SecurityRPCService.class);

    private DataSourceTextField emailField;
    private DataSourcePasswordField passwordField;
    private HeaderItem errorsItem;
    private HeaderItem headerItem;
    private PasswordItem passwordItem;
    private TextItem emailItem;
//    private IButton validateItem;

    public LoginForm() {

        Log.debug("Initialization of LoginForm starts..");

        emailField = new DataSourceTextField("email", "Email", 100, true);
        RegExpValidator emailValidator = new RegExpValidator();

        errorsItem = new HeaderItem();
        errorsItem.setTextBoxStyle(CSS.GENERAL_ERRORS_LABEL);
        errorsItem.setDefaultValue("");

        emailValidator.setErrorMessage("Invalid email address");
        emailValidator.setExpression(REGEX.EMAIL);

        emailField.setValidators(emailValidator);

        passwordField = new DataSourcePasswordField("password", "Password", 20, true);
        passwordItem = new PasswordItem("password", "Hasło");

        emailItem = new TextItem("email", "login uzytkownika");

        TextItem emailItem2 = new TextItem("email2", "login uzytkownika");

        this.setUseAllDataSourceFields(true);

//        validateItem = new IButton();
//        validateItem.setVisible(true);
//        validateItem.setTitle("Send");
//        validateItem.addClickHandler(new ClickHandler() {
//            public void onClick(ClickEvent event) {
//                sendFormData();
//            }
//        });

        this.setFields(errorsItem, emailItem, passwordItem);


        Log.debug("Initialization of LoginForm ends..");
    }

//    protected void sendFormData() {
//
//        Log.debug("sendFormData..");
//
//        if (validate()) {
//            AsyncCallback<LoginResult> callback = new AsyncCallback<LoginResult>() {
//                public void onFailure(Throwable caught) {
//                    Log.debug("Error occured " + caught.getMessage());
//
//                    setErrorMessage("Wystąpił błąd : " + caught.getMessage());
//                }
//
//                public void onSuccess(LoginResult result) {
//
//                    Log.debug("Success : " + result);
//
//                    if (result.isSuccess()) {
//                        redirectOnSuccess(result.getReferrerUrl());
//                    } else {
//                        setErrorMessage(result.getErrorMessage());
//                    }
//                }
//            };
//
//            service.login (
//                    getValue("email").toString(),
//                    getValue("password").toString(),
//                    true,
//                    callback
//            );
//        }
//        else {
//            setErrorMessage("Formularz nie jest poprawnie wypełniony!");
//        }
//
//    }

//    private void setErrorMessage(String errorMessage) {
//        errorsItem.setValue(errorMessage);
//    }

    private void redirectOnSuccess(String url) {
        if (url != null) {
            Window.Location.replace(url);
        } else {
            Window.Location.replace(GWT.getHostPageBaseURL());
        }
    }


}