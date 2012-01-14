package war.client.ui.widget;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import war.client.service.GreetingService;
import war.client.service.GreetingServiceAsync;
import war.server.core.entity.User;

import java.util.List;

public class ContentBox extends HLayout {

    private static final int CONTEXT_BOX_HEIGHT = 600;

    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    Label label;

    public ContentBox() {
        super();

        Log.debug("Initializing ContextBox widget..");

        // initialise the layout container
        this.setHeight(CONTEXT_BOX_HEIGHT);
        this.setBackgroundColor("#4096EE");

        // initialise the application menu label
        label = new Label();
        label.setContents("Main content box");
        label.setAlign(Alignment.CENTER);
        label.setOverflow(Overflow.HIDDEN);

        // add the label to the layout container
        this.addMember(label);

        IButton button = new IButton("Hello World");
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {

                greetingService.greetServer(new AsyncCallback<List<User>>() {
                    public void onFailure(Throwable caught) {
                        SC.say("Hello World from SmartGWT failed");
                    }

                    @Override
                    public void onSuccess(List<User> result) {
                        String message = " Hello man! ";

                        for(User u : result) {
                            message += u.getUsername();
                        }

                        SC.say(message);
                    }

                });
            }
        });

        this.addMember(button);

        Log.debug("ContentBox widget was initialized..");
    }
}

