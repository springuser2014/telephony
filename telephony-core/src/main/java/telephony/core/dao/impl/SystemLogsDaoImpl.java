package telephony.core.dao.impl;

import telephony.core.dao.SystemLogsDao;
import telephony.core.entity.jpa.SystemLog;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class SystemLogsDaoImpl extends GenericDaoImpl<SystemLog> implements SystemLogsDao {

    /**
     * asd.
     */
    public SystemLogsDaoImpl() {
        super(SystemLog.class);
    }

}
