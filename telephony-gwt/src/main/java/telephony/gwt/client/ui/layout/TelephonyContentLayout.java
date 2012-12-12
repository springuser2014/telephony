package telephony.gwt.client.ui.layout;

import com.smartgwt.client.widgets.layout.HLayout;
import telephony.gwt.client.gwtp.view.BlueBox;


public class TelephonyContentLayout extends HLayout implements TelephonyContent {

    public TelephonyContentLayout() {
        this.setWidth100();
        this.setHeight("100%");
        this.addMember(new BlueBox("100%", "100%", "width 100%"));
        this.setMembersMargin(0);
        this.setLayoutMargin(0);
        this.setMargin(0);
        this.setPadding(0);
    }
}
