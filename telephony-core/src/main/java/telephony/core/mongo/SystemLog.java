package telephony.core.mongo;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
@Entity("system_logs")
public class SystemLog {

    /**
     * asd.
     */
    @Id
    private ObjectId id;


    /**
     * asd.
     * @return asd.
     */
    public final ObjectId getId() {

        return id;
    }

    /**
     * asd.
     * @param iD asd.
     */
    public final void setId(final ObjectId iD) {

        this.id = iD;
    }




}

