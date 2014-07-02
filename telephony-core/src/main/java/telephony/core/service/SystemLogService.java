package telephony.core.service;

import java.util.List;

import telephony.core.entity.mongo.SystemLog;
import telephony.core.entity.mongo.SystemLogQuery;


/**
 * asd.
 */
public interface SystemLogService {

    /**
     * asd.
     * @param query asd.
     * @return asd.
     */
    List<SystemLog> get(SystemLogQuery query);
}
