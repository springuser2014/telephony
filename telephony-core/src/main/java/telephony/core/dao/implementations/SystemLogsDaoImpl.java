package telephony.core.dao.implementations;

import telephony.core.dao.interfaces.SystemLogsDao;
import telephony.core.entity.SystemLog;

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
