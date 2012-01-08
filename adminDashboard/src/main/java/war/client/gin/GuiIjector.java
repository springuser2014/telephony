package war.client.gin;


import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import org.jboss.ejb3.common.proxy.plugins.async.AsyncProvider;
import war.client.GreetingService;
import war.client.ui.widgets.ContentBox;
import war.client.ui.widgets.TopMenu;

@GinModules(TelephonyClientModule.class)
public interface GuiIjector extends Ginjector {
  TopMenu getTopMenu();
  ContentBox getContentBox();
}