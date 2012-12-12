package telephony.gwt.client.ui.layout;

import com.smartgwt.client.widgets.layout.VLayout;
import telephony.gwt.client.gwtp.view.BlueBox;

public class TelephonyHeaderLayout extends VLayout implements TelephonyHeader {
    public TelephonyHeaderLayout() {
        this.setWidth100();
        this.setHeight(200);
        this.setMinHeight(200);
        this.addMember(new BlueBox("100%", "100%", "width 100%"));
        this.setLayoutMargin(0);
        this.setMargin(0);
        this.setPadding(0);
    }

}
