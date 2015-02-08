package telephony.core.service;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import telephony.core.dao.UsersDao;
import telephony.core.service.dto.request.SessionDetailsRequest;
import telephony.core.service.dto.response.SessionDetailsResponse;
import telephony.core.service.exception.SessionServiceException;
import telephony.core.service.dto.SessionDto;
import telephony.core.util.StringGenerator;

public interface SessionManager {
	
	Integer getSessionValidity();
	
	@Inject
	void setSessionValidity(@Named("sessionValidity") Integer sessionValidity);
	
	void setUsersDao(UsersDao usersDao);
	
	@Inject
	void setStringGenerator(StringGenerator stringGenerator);

    SessionDto init(String username, String password)
			throws SessionServiceException;

    SessionDto refresh(SessionDto sessionToRefresh)
			throws SessionServiceException;

    boolean destroy(SessionDto sessionToDelete)
			throws SessionServiceException;
    
    boolean validate(SessionDto sessionToValidate)
			throws SessionServiceException;

	SessionDetailsResponse fetchDetails(SessionDetailsRequest request)
			throws SessionServiceException;
}
