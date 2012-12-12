package telephony.gwt.client.ui.layout;

import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.layout.VLayout;


public class TelephonyMainLayout extends VLayout implements TelephonyMain {

    public TelephonyMainLayout() {
        this.setWidth("100%");
        this.setHeight100();
        this.setAlign(VerticalAlignment.TOP);
        this.setMembersMargin(0);
        this.setLayoutMargin(0);
        this.setMargin(0);
        this.setPadding(0);
    }
}
