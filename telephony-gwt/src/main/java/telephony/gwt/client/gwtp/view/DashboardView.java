package telephony.gwt.client.gwtp.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import telephony.gwt.client.gin.GuiInjector;
import telephony.gwt.client.gwtp.presenter.DashboardPresenter;
import telephony.gwt.client.ui.layout.TelephonyContentLayout;
import telephony.gwt.client.ui.layout.TelephonyHeaderLayout;
import telephony.gwt.client.ui.layout.TelephonyMainLayout;


/**
 * @todo refactor me!
 */
public class DashboardView extends ViewImpl
        implements DashboardPresenter.StartView {

    private TelephonyMainLayout newMainLayout;
    private TelephonyHeaderLayout headerLayout;
    private TelephonyContentLayout contentLayout;

//    @ContentSlot
//    public static final GwtEvent.Type<RevealContentHandler<?>> TYPE_SetHeaderContent = new GwtEvent.Type<RevealContentHandler<?>>();
//
//    @ContentSlot
//    public static final GwtEvent.Type<RevealContentHandler<?>> TYPE_SetMainContent = new GwtEvent.Type<RevealContentHandler<?>>();

    private final GuiInjector injector = GWT.create(GuiInjector.class);


    public DashboardView() {

        newMainLayout = injector.getMainLayout();
        headerLayout = injector.getHeaderLayout();
        contentLayout = injector.getContentLayout();

        newMainLayout.addMember(headerLayout);
        newMainLayout.addMember(contentLayout);
    }

    public Widget asWidget() {
        return newMainLayout;
    }

    public TelephonyHeaderLayout getHeaderLayout() {
        return headerLayout;
    }

    public TelephonyContentLayout getContentLayout() {
        return contentLayout;
    }

    public String getName() {
        return "";

    }

    public Button getSendButton() {
        return null;

    }

    public void resetAndFocus() {
    }

    public void setError(String errorText) {

    }

}