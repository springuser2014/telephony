package telephony.core.service;

import javax.persistence.EntityManager;

import telephony.core.entity.jpa.BaseEntity;

/**
 * asd.
 */
public interface BasicService<T extends BaseEntity> {

    /**
     * asd.
     * @return asd.
     */
    EntityManager getEntityManager();
    
    /**
     * asd 
     * @return adsd.
     */
    <T> long count();
}
