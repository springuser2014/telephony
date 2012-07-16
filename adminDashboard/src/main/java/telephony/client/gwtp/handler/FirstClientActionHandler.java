package telephony.client.gwtp.handler;


import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.client.actionhandler.AbstractClientActionHandler;
import com.gwtplatform.dispatch.client.actionhandler.ExecuteCommand;
import com.gwtplatform.dispatch.client.actionhandler.UndoCommand;


import telephony.client.gwtp.action.MyFirstAction;
import telephony.client.gwtp.result.MyFirstResult;

public class FirstClientActionHandler extends AbstractClientActionHandler<MyFirstAction, MyFirstResult> {

    @Inject
    public FirstClientActionHandler() {
        super(MyFirstAction.class);
    }

    @Override
    public void execute(final MyFirstAction action, final AsyncCallback<MyFirstResult> resultCallback, ExecuteCommand<MyFirstAction, MyFirstResult> dispatch) {
        
        final MyFirstResult result = null;
        
        if (result != null) {
            resultCallback.onSuccess(result);
        }
        else {
            dispatch.execute(action, new AsyncCallback<MyFirstResult>() {
                public void onFailure(Throwable caught) {
                    resultCallback.onFailure(caught);
                }

                public void onSuccess(MyFirstResult result) {
                    resultCallback.onSuccess(result);
                }
            });
        }
    }

    @Override
    public void undo(MyFirstAction action, MyFirstResult result, AsyncCallback<Void> callback, UndoCommand<MyFirstAction, MyFirstResult> dispatch) {
        dispatch.undo(action, result, callback);
    }
}
