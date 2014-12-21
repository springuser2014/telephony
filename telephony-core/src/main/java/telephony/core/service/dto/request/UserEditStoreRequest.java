package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

import java.util.ArrayList;
import java.util.List;

public class UserEditStoreRequest extends AuthRequest {

    List<Long> storesToAdd;
    List<Long> storesToDelete;

    public UserEditStoreRequest() {
        super();
        storesToAdd = new ArrayList<Long>();
        storesToDelete = new ArrayList<Long>();
    }

    public UserEditStoreRequest(SessionDto sessionDto) {
        super(sessionDto);
        storesToAdd = new ArrayList<Long>();
        storesToDelete = new ArrayList<Long>();
    }

    public List<Long> getStoresToAdd() {
        return storesToAdd;
    }

    public List<Long> getStoresToDelete() {
        return storesToDelete;
    }

    public void addStoreToAdd(Long storeId) {
        if (!storesToAdd.contains(storeId)) {
            storesToAdd.add(storeId);
        }
    }

    public void removeStoreToAdd(Long storeId) {
        if (storesToAdd.contains(storeId)) {
            storesToAdd.remove(storeId);
        }
    }

    public void addStoreToDelete(Long storeId) {
        if (!storesToDelete.contains(storeId)) {
            storesToDelete.add(storeId);
        }
    }

    public void removeStoreToDelete(Long storeId) {
        if (storesToDelete.contains(storeId)) {
            storesToDelete.remove(storeId);
        }
    }

}
