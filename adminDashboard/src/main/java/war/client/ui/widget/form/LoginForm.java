package war.client.ui.widget.form;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourcePasswordField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.HeaderItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;

import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import war.client.configuration.Regex;


public class LoginForm extends DynamicForm {

    public LoginForm() {

        DataSource dataSource = new DataSource();

        DataSourceTextField emailField = new DataSourceTextField("email", "Email", 100, true);
        RegExpValidator emailValidator = new RegExpValidator();
        emailValidator.setErrorMessage("Invalid email address");
        emailValidator.setExpression(Regex.EMAIL);

        emailField.setValidators(emailValidator);

        DataSourcePasswordField passwordField = new DataSourcePasswordField("password", "Password", 20, true);
        dataSource.setFields(emailField, passwordField);


        this.setDataSource(dataSource);
        this.setUseAllDataSourceFields(true);

        HeaderItem header = new HeaderItem();
        header.setDefaultValue("Logowanie");

        PasswordItem passwordItem = new PasswordItem();
        passwordItem.setName("Hasło");

        ButtonItem validateItem = new ButtonItem();
        validateItem.setTitle("Send");
        validateItem.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                validate(false);
            }
        });

        this.setFields(header, passwordItem, validateItem);
        this.setValue("email", "bob@.com");
    }
}
