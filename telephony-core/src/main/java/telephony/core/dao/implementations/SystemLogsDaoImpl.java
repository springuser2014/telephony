package telephony.core.dao.implementations;

import telephony.core.dao.interfaces.SystemLogsDao;
import telephony.core.entity.SystemLog;

public class SystemLogsDaoImpl extends GenericDaoImpl<SystemLog> implements SystemLogsDao {
    public SystemLogsDaoImpl() {
        super(SystemLog.class);
    }

}
