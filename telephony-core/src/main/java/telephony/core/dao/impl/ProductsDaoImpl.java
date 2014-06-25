package telephony.core.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.persist.Transactional;

import telephony.core.dao.ProductsDao;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductStatus;
import telephony.core.entity.jpa.Sale;
import telephony.core.entity.jpa.Store;
import telephony.core.service.ProductQueryCriteria;

/**
 * Products management DAO.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class ProductsDaoImpl extends GenericDaoImpl<Product> implements ProductsDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * asd.
     */
    public ProductsDaoImpl() {
        super(Product.class);
    }

    /**
     * {@inheritDoc}
     */
	@Override
    @SuppressWarnings("unchecked")
    public List<Product> findCreatedByUserId(Long userId, ProductStatus productStatus) {
        logger.debug("findCreatedByUserId starts");
        logger.debug("params : [ userId : {} , productStatus : {} ]", userId, productStatus);

        List<Product> res =
        getEntityManager()
        .createQuery("select p from Product p where p.creator = ?1 and p.status = ?2 ")
                .setParameter(1, userId)
                .setParameter(2, productStatus)
                .getResultList();

        logger.debug("findCreatedByUserId returns {} elements", res.size());

        return res;
    }

    /**
     * {@inheritDoc}
     */
	@Override
    @SuppressWarnings("unchecked")
    public List<Product> findByStore(Store store) {
        logger.debug("findByStore starts");
        logger.debug("params : [ storeId : {}  ]", store);

        List<Product> res = getEntityManager()
		.createQuery("select p from Product p where p.store = ?1")
            .setParameter(1, store)
            .getResultList();
        
        logger.debug("findByStore returns {} elements", res.size());

        return res;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Product> findByCriteria(
        ProductQueryCriteria query) {

        StringBuilder sb = new StringBuilder();
        sb.append(" select p from Product p ");
        sb.append(" join fetch p.delivery d ");
        sb.append(" join fetch p.store st ");
        sb.append(" left join fetch p.sale sa ");
        sb.append(" where 1=1 ");

        if (query.getImei() != null && query.getImei().length() > 0) {
            sb.append("and p.imei = :imei ");
        }

        if (query.getProducer() != null && query.getProducer().length() > 0) {
            sb.append("and p.producer = :producer ");
        }

        if (query.getModel() != null && query.getModel().length() > 0) {
            sb.append("and p.model = :model ");
        }

        if (query.getColor() != null && query.getColor().length() > 0) {
            sb.append("and p.color = :color ");
        }

        if (query.getStoreId() != null && query.getStoreId() > 0) {
            sb.append("and st.id = :storeId ");
        }

        if (query.getDeliveryDateStart() != null) {
            sb.append("and d.dateIn >= :deliveryDateStart ");
        }

        if (query.getDeliveryDateEnd() != null) {
            sb.append("and d.dateIn <= :deliveryDateEnd ");
        }

        if (query.getStatus() == ProductStatus.IN_STORE) {
            sb.append("and sa.id is null ");
        } else if (query.getStatus() == ProductStatus.SOLD) {
            sb.append("and sa.id is not null ");
        }

        List<Product> res = null;

        Query jpaQuery = getEntityManager().createQuery(sb.toString());

        if (query.getImei() != null && query.getImei().length() > 0) {
            jpaQuery.setParameter("imei", query.getImei());
        }

        if (query.getProducer() != null && query.getProducer().length() > 0) {
        	jpaQuery.setParameter("producer", query.getProducer());
        }

        if (query.getModel() != null && query.getModel().length() > 0) {
        	jpaQuery.setParameter("model", query.getModel());
        }

        if (query.getColor() != null && query.getColor().length() > 0) {
        	jpaQuery.setParameter("color", query.getColor());
        }

        if (query.getStoreId() != null && query.getStoreId() > 0) {
        	jpaQuery.setParameter("storeId", query.getStoreId());
        }

        if (query.getDeliveryDateStart() != null) {
            Timestamp deliveryDateStartTmp = new Timestamp(query.getDeliveryDateStart().getTime());
            jpaQuery.setParameter("deliveryDateStart", deliveryDateStartTmp);
        }

        if (query.getDeliveryDateEnd() != null) {
            Timestamp deliveryDateEndTmp = new Timestamp(query.getDeliveryDateEnd().getTime());
            jpaQuery.setParameter("deliveryDateEnd", deliveryDateEndTmp);
        }

        res = jpaQuery.getResultList();

        logger.info(" size : {}", res.size());


        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Product findByImei(String imei) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> fetchImeisList() {
        logger.debug("ProductServiceImpl.fetchImeisList starts");

        List<Product> list = this.find();
        List<String> res = new ArrayList<String>();

        for (Product p : list) {
            res.add(p.getImei());
        }

        logger.debug("ProductServiceImpl.fetchImeisList ends");

        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> fetchProducersList() {
        logger.debug("ProductServiceImpl.fetchProducersList starts");

        List<Product> list = this.find();
        List<String> res = new ArrayList<String>();

        for (Product p : list) {
//            if (!res.contains(p.getProducer())) {
//                res.add(p.getProducer());
//            }
        }

        logger.debug("found {} elements", res.size());

        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> fetchModelsList() {
        logger.debug("ProductServiceImpl.fetchModelsList starts");

        List<Product> list = this.find();
        List<String> res = new ArrayList<String>();

        for (Product p : list) {
            if (!res.contains(p.getModel())) {
//                res.add(p.getModel());
            }
        }

        logger.debug("found {} elements", res.size());

        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Product findByImeiAndStoreId(String imei, Long storeId) {
        logger.debug("ProductServiceImpl.findByImeiAndStoreId starts");
        logger.debug("params : [ imei : {} , storeId : {} ]", imei, storeId);

        Product res = (Product) getEntityManager().createQuery("select p from Product p "
                + "left join p.store st "
                + "left join p.sale sa "
                + "where st.id = ?1 and p.imei = ?2 and sa.id is null")
                .setParameter(1, storeId)
                .setParameter(2, imei)
                .getSingleResult();

        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
	@Override
    @SuppressWarnings("unchecked")
    public List<Product> find() {

        logger.debug("ProductsDaoImpl.findAll starts ");
        logger.debug("entity type : {} ", getEntityClass().getName());

		List<Product> lst =
			getEntityManager().createQuery(
				"select e from Product e "
                + " join fetch e.delivery d "
                + " join fetch e.store s "
                + " left join fetch e.sale sa ")
                .getResultList();

        logger.debug("found {} elements", lst.size());

        return lst;
    }

    @SuppressWarnings("unchecked")
	@Override
	public List<Product> findByStoreAndStatus(Long storeId,
			ProductStatus productStatus) {
		
    	 logger.debug("ProductServiceImpl.findByImeiAndStoreId starts");
         logger.debug("params : [ productStatus : {} , storeId : {} ]", 
        		 productStatus.toString(), storeId);
         
         List<Product> lst = null;
         
         if (productStatus.toString().equals(ProductStatus.IN_STORE.toString())) {
        	 lst = (List<Product>) getEntityManager()
	         .createQuery("select p from Product p "
             + "left join p.store st "
             + "left join p.sale sa "
             + "where st.id = ?1 and sa.id is null")
             .setParameter(1, storeId)                 
             .getResultList();
        	 
         } else if (productStatus.toString().equals(ProductStatus.SOLD.toString())) {
        	 lst = (List<Product>) getEntityManager()
	         .createQuery("select p from Product p "
             + "left join p.store st "
             + "left join p.sale sa "
             + "where st.id = ?1 and sa.id is not null")
             .setParameter(1, storeId)                 
             .getResultList();
         }
	         
         return lst;
    }

	/**
     * {@inheritDoc}
     */
    @Transactional
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findBySale(Sale sale) {

        logger.debug("ProductsDaoImpl.findBySale starts ");
        logger.debug("entity type : {} ", getEntityClass().getName());

		List<Product> lst = (List<Product>)
			getEntityManager()
			.createQuery(
				"select e from Product e " +
				"join fetch e.delivery d " +
				"join fetch e.store s left " +
				"join fetch e.sale sa where e.sale = ?1")
				.setParameter(1, sale)
                .getResultList();

        logger.debug("found {} elements", lst.size());
		return lst;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findByIMEIs(List<String> imeis) {
	
	    logger.debug("ProductsDaoImpl.findByIMEIs starts");
        logger.debug("entity type : {} ", getEntityClass().getName());
        
        List<Product> lst = (List<Product>)
        		getEntityManager()
        		.createQuery(
        		"select e from Product e " +
        		"where e.imei in (?1)")
				.setParameter(1, imeis)
				.getResultList();
        
        logger.debug("found {} elements", lst.size());
        
        return lst;
	}
    
    
    
    
}
