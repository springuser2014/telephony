package telephony;

import org.mockito.Mock;
import telephony.gwt.client.gwtp.handler.FirstClientActionHandler;
import telephony.gwt.client.gwtp.presenter.LoginPresenter;

import static org.junit.Assert.assertTrue;

//@RunWith(MockitoJUnitRunner.class)
public class LoginWizardTest {

    @Mock
    private LoginPresenter.LoginView view;

    @Mock
    private LoginPresenter.LoginProxy proxy;

    @Mock
    private FirstClientActionHandler firstClientActionHandler;

    private LoginPresenter presenter;

//    @Before
    public void setUp() {

//        Injector injector = Guice.createInjector(new MockHandlerModule() {
//            @Override
//            protected void configureMockHandlers() {
//                bindMockActionHandler(new MyFirstAction(), new MyFirstResult(), firstClientActionHandler);
//                bindMockClientActionHandler();
//                bindMockActionHandler(,firstClientActionHandler);
//            }
//        });


//        DispatchAsync dispatcher = injector.getInstance(DispatchAsync.class);
//
//        presenter = new LoginPresenter(null, view, proxy, dispatcher, null);
//
//        presenter.onOkButtonClicked();


    }

//    @Test
    public void test() {
        assertTrue(true);
    }
}
