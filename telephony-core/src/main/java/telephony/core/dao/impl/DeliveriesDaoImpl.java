package telephony.core.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.DeliveriesDao;
import telephony.core.entity.jpa.Delivery;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.DeliveryFilterCriteria;

import static telephony.core.assertion.CommonAssertions.isNotNull;

public class DeliveriesDaoImpl
extends GenericDaoImpl<Delivery> 
implements DeliveriesDao {

    final Logger logger = LoggerFactory.getLogger(getClass());

    public DeliveriesDaoImpl() {
        super(Delivery.class);
    }

	@Override
    @SuppressWarnings("unchecked")
	public List<Product> findProductsByDeliveriesIds(List<Long> ids) {

        logger.debug("DeliveriesDaoImpl.findProductsByDeliveriesIds starts");

		List<Product> result =
			getEntityManager().createQuery(
        	"  select p from Product p "
            + " join fetch p.delivery d"
            + " join fetch d.store s"
            + " where d.id in (?1) "
            + " and p.deleter is null "
            + " order by d.id desc ")
            .setParameter(1, ids)
            .getResultList();

        logger.debug(" found {} elements", result.size());

        return result;
    }

    @Override
    public long getNumberOfDeliveries(Store store) {
    	
   	 logger.debug("DeliveriesDaoImpl.getNumberOfDeliveries starts");

        Query query = 
			getEntityManager()
			.createQuery("select count(d) from Delivery d "
	        + " inner join d.store s "
	        + " where d.deleter is null "
	        + " and s.id = ?1 ")
	        .setParameter(1, store.getId());

        Number res = (Number) query.getSingleResult();
        Long res2 = (Long) res;

        return res2;
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Delivery> findByStore(Store stores) {
		
		Query query = getEntityManager()
			.createQuery("select e from Delivery e where e.store = ?1")
			.setParameter(1, stores);
		
		List<Delivery> res = (List<Delivery>) query.getResultList();
		
		return res;
	}

	@Override
	public Delivery findDetailsById(Long deliveryId) {
		Query query = getEntityManager()
		.createQuery(
			" select d from Delivery d" +
			" join fetch d.products p " +			            
            " where d.id in (?1) " +              
            " order by d.id desc ")
            .setParameter(1, deliveryId);
		
		List<Delivery> res = (List<Delivery>) query.getResultList();
		
		if (res.size() > 0) {
			return res.get(0);
		} else {
			return null;
		}
	}
	
	// TODO : extract to interface searchable
	@Override
    @SuppressWarnings("unchecked")
	public List<Delivery> find(DeliveryFilterCriteria filters) {

        logger.debug("findAll starts ");
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("select DISTINCT e from Delivery e ");
        sb.append("inner join e.products p ");
		sb.append("inner join e.store s ");
		sb.append("inner join e.contact c ");
		sb.append("inner join p.model m ");
		sb.append("inner join m.producer pr ");
        sb.append("where 1=1 ");
        
        if (filters.getDeliveredBy() != null) {
        	sb.append(" and c.id = :deliveredBy ");
        }
        
        if (filters.getRegisteredBy() != null) {
        	
        }
        
        if (filters.getLabel() != null) {
        	sb.append(" and e.label LIKE :label ");
        }
        
        if (filters.getDeliveryDateStart() != null) {
        	sb.append(" and e.dateIn >= :deliveryDateStart ");        	
        }

        if (filters.getDeliveryDateEnd() != null) {
        	sb.append(" and e.dateIn <= :deliveryDateEnd ");
        }

        if (filters.getContactId() != null) {
            sb.append(" and c.id = :contactId ");
        }

        if (filters.getStoreId() != null) {
            sb.append(" and s.id = :storeId ");
        }
        
        if (filters.getMinNumberOfProducts() != null ||  
    		filters.getMaxNumberOfProducts() != null ||  
    		filters.getSumFrom() != null || 
    		filters.getSumTo() != null) 
        {
        	sb.append(" group by e.id ");
        	sb.append(" having 1=1 ");
        	
	        if (filters.getMinNumberOfProducts() != null) {
	        	sb.append(" and count(e.id) <= :max ");
	        } 

	        if (filters.getMaxNumberOfProducts() != null) {
	        	sb.append(" and count(e.id) >= :min ");
	        }
	        
	        if (filters.getSumTo() != null) {
	        	sb.append(" and sum(p.priceIn) <= :sumTo ");
	        } 
	        
	        if (filters.getSumFrom() != null) {
	        	sb.append(" and sum(p.priceIn) >= :sumFrom ");	        	
	        }    
        }
        
        String queryStr = sb.toString();
        
        Query query = getEntityManager()
            			.createQuery(queryStr);
        
        if (filters.getDeliveryDateStart() != null) {
        	Timestamp deliveryDateStart = new Timestamp(filters.getDeliveryDateStart().getTime());
        	query.setParameter("deliveryDateStart", deliveryDateStart);
        }

        if (filters.getDeliveryDateEnd() != null) {
        	Timestamp deliveryDateEnd= new Timestamp(filters.getDeliveryDateEnd().getTime());
        	query.setParameter("deliveryDateEnd", deliveryDateEnd);
        }        
        
        if (filters.getLabel() != null) {
        	query.setParameter("label", "%" +  filters.getLabel() + "%");
        }
        
        if (filters.getDeliveredBy() != null) {
        	query.setParameter("deliveredBy", filters.getDeliveredBy());
        }

        if (filters.getMinNumberOfProducts() != null) {
        	query.setParameter("min", new Long(filters.getMinNumberOfProducts()));
        }
        
        if (filters.getMaxNumberOfProducts() != null) {
        	query.setParameter("max", new Long(filters.getMaxNumberOfProducts()));
        }

        if (filters.getSumFrom() != null) {
        	query.setParameter("sumFrom", filters.getSumFrom());
        }
        
        if (filters.getSumFrom() != null) {
        	query.setParameter("sumTo", filters.getSumTo());
        }

        if (filters.getContactId() != null) {
            query.setParameter("contactId", filters.getContactId());
        }

        if (filters.getStoreId() != null) {
            query.setParameter("storeId", filters.getStoreId());
        }

        if (isNotNull(filters.getPage()) && isNotNull(filters.getPerPage())) {
            query.setFirstResult((filters.getPerPage())* filters.getPage());
            query.setMaxResults(filters.getPerPage());
        }

        List<Delivery> lst = query.getResultList();

        logger.debug("found {} elements", lst.size());

        return lst;
    }

    @Override
    public Long count(DeliveryFilterCriteria filters) {
        logger.debug("findAll starts ");

        StringBuilder sb = new StringBuilder();

        sb.append("select count(DISTINCT e.id) from Delivery e ");
        sb.append("inner join e.products p ");
        sb.append("inner join e.store s ");
        sb.append("inner join e.contact c ");
        sb.append("inner join p.model m ");
        sb.append("inner join m.producer pr ");
        sb.append("where 1=1 ");

        if (filters.getDeliveredBy() != null) {
            sb.append(" and c.id = :deliveredBy ");
        }

        if (filters.getRegisteredBy() != null) {

        }

        if (filters.getLabel() != null) {
            sb.append(" and e.label LIKE :label ");
        }

        if (filters.getDeliveryDateStart() != null) {
            sb.append(" and e.dateIn >= :deliveryDateStart ");
        }

        if (filters.getDeliveryDateEnd() != null) {
            sb.append(" and e.dateIn <= :deliveryDateEnd ");
        }

        if (filters.getContactId() != null) {
            sb.append(" and c.id = :contactId ");
        }

        if (filters.getStoreId() != null) {
            sb.append(" and s.id = :storeId ");
        }

        if (filters.getMinNumberOfProducts() != null ||
                filters.getMaxNumberOfProducts() != null ||
                filters.getSumFrom() != null ||
                filters.getSumTo() != null)
        {
            sb.append(" group by e.id ");
            sb.append(" having 1=1 ");

            if (filters.getMinNumberOfProducts() != null) {
                sb.append(" and count(e.id) <= :max ");
            }

            if (filters.getMaxNumberOfProducts() != null) {
                sb.append(" and count(e.id) >= :min ");
            }

            if (filters.getSumTo() != null) {
                sb.append(" and sum(p.priceIn) <= :sumTo ");
            }

            if (filters.getSumFrom() != null) {
                sb.append(" and sum(p.priceIn) >= :sumFrom ");
            }
        }

        String queryStr = sb.toString();

        Query query = getEntityManager()
                .createQuery(queryStr);

        if (filters.getDeliveryDateStart() != null) {
            Timestamp deliveryDateStart = new Timestamp(filters.getDeliveryDateStart().getTime());
            query.setParameter("deliveryDateStart", deliveryDateStart);
        }

        if (filters.getDeliveryDateEnd() != null) {
            Timestamp deliveryDateEnd= new Timestamp(filters.getDeliveryDateEnd().getTime());
            query.setParameter("deliveryDateEnd", deliveryDateEnd);
        }

        if (filters.getLabel() != null) {
            query.setParameter("label", "%" +  filters.getLabel() + "%");
        }

        if (filters.getDeliveredBy() != null) {
            query.setParameter("deliveredBy", filters.getDeliveredBy());
        }

        if (filters.getMinNumberOfProducts() != null) {
            query.setParameter("min", new Long(filters.getMinNumberOfProducts()));
        }

        if (filters.getMaxNumberOfProducts() != null) {
            query.setParameter("max", new Long(filters.getMaxNumberOfProducts()));
        }

        if (filters.getSumFrom() != null) {
            query.setParameter("sumFrom", filters.getSumFrom());
        }

        if (filters.getSumFrom() != null) {
            query.setParameter("sumTo", filters.getSumTo());
        }

        if (filters.getContactId() != null) {
            query.setParameter("contactId", filters.getContactId());
        }

        if (filters.getStoreId() != null) {
            query.setParameter("storeId", filters.getStoreId());
        }


        // TODO improve it later
        List<Long> lst = (List<Long>) query.getResultList();
        long count = 0;

        for (Long l : lst) {
            count += l;
        }

        return count;
    }

}
