package telephony.core.service.interfaces;

import telephony.core.service.Session;

public interface SessionService {

    public Session init(String username, String password);

    public Session refresh(String username, String sessionId);

    public boolean destroy(String username, String sessionId);

}
