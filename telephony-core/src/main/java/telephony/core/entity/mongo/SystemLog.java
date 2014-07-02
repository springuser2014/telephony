package telephony.core.entity.mongo;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;

/**
 * asd.
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

