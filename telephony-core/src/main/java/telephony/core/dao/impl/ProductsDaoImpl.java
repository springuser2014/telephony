package telephony.core.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.persist.Transactional;

import telephony.core.dao.ProductsDao;
import telephony.core.entity.jpa.*;
import telephony.core.query.filter.DeliveryFilterCriteria;
import telephony.core.query.filter.ProductFilterCriteria;

import static telephony.core.assertion.CommonAssertions.*;


public class ProductsDaoImpl 
extends GenericDaoImpl<Product> 
implements ProductsDao {

    final Logger logger = LoggerFactory.getLogger(getClass());

    public ProductsDaoImpl() {
        super(Product.class);
    }

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

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> findByCriteria(ProductFilterCriteria query) {

        StringBuilder sb = new StringBuilder();
        sb.append(" select distinct p from Product p ");
        sb.append(" join p.delivery d ");
        sb.append(" join p.store st ");
        sb.append(" join p.model m ");
        sb.append(" join m.producer pr ");
        sb.append(" left join p.sale sa ");
        sb.append(" where 1=1 ");

        if (query.getImei() != null && query.getImei().length() > 0) {
            sb.append("and p.imei = :imei ");
        }

        if (query.getProducer() != null && query.getProducer().length() > 0) {
            sb.append("and pr.label = :producer ");
        }

        if (query.getModel() != null && query.getModel().length() > 0) {
            sb.append("and m.label = :model ");
        }

        if (query.getColor() != null && query.getColor().length() > 0) {
            sb.append("and p.color = :color ");
        }

        if (query.getStoreId() != null && query.getStoreId() > 0) {
            sb.append("and st.id = :storeId ");
        }

        if (isNotNull(query.getDeliveryDateStart())) {
            sb.append("and d.dateIn >= :deliveryDateStart ");
        }

        if (isNotNull(query.getDeliveryDateEnd())) {
            sb.append("and d.dateIn <= :deliveryDateEnd ");
        }

        if (isNotNull(query.getStatus())) {
            if (query.getStatus() == ProductStatus.IN_STORE) {
                sb.append("and sa.id is null ");
            } else if (query.getStatus() == ProductStatus.SOLD) {
                sb.append("and sa.id is not null ");
            }
        }

        if (isNotEmpty(query.getDeliveriesIds())) {
            sb.append("and d.id IN (:deliveriesIds) ");
        }

        if (isNotEmpty(query.getSalesIds())) {
            sb.append("and d.id IN (:salesIds) ");
        }

        if (isNotEmpty(query.getProductIds())) {
            sb.append("and p.id in (:productIds) ");
        }

        List<Product> res = null;

        Query jpaQuery = getEntityManager()
        					.createQuery(sb.toString());

        if (query.getImei() != null && query.getImei().length() > 0) {
            jpaQuery.setParameter("imei", query.getImei());
        }

        if (query.getProducer() != null && query.getProducer().length() > 0) {
        	jpaQuery.setParameter("producer", query.getProducer());
        }

        if (isNotEmpty(query.getModel())) {
        	jpaQuery.setParameter("model", query.getModel());
        }

        if (isNotEmpty(query.getColor())) {
        	jpaQuery.setParameter("color", query.getColor());
        }

        if (query.getStoreId() != null && query.getStoreId() > 0) {
        	jpaQuery.setParameter("storeId", query.getStoreId());
        }

        if (isNotNull(query.getDeliveryDateStart())) {
            Timestamp deliveryDateStartTmp = new Timestamp(query.getDeliveryDateStart().getTime());
            jpaQuery.setParameter("deliveryDateStart", deliveryDateStartTmp);
        }

        if (isNotNull(query.getDeliveryDateEnd())) {
            Timestamp deliveryDateEndTmp = new Timestamp(query.getDeliveryDateEnd().getTime());
            jpaQuery.setParameter("deliveryDateEnd", deliveryDateEndTmp);
        }

        if (isNotEmpty(query.getProductIds())) {
            jpaQuery.setParameter("productIds", query.getProductIds());
        }

        if (isNotEmpty(query.getDeliveriesIds())) {
            jpaQuery.setParameter("deliveriesIds", query.getDeliveriesIds());
        }

        if (isNotEmpty(query.getSalesIds())) {
            jpaQuery.setParameter("salesIds", query.getSalesIds());
        }

        if (isNotNull(query.getPage()) && isNotNull(query.getPerPage())) {
            jpaQuery.setFirstResult((query.getPerPage())* query.getPage());
            jpaQuery.setMaxResults(query.getPerPage());
        }

        res = jpaQuery.getResultList();

        logger.info(" size : {}", res.size());

        return res;
    }

    @Override
    public List<String> fetchImeisList() {
        logger.debug("ProductServiceImpl.fetchImeisList starts");

        List<Product> list = this.find(null);
        List<String> res = new ArrayList<String>();

        for (Product p : list) {
            res.add(p.getImei());
        }

        logger.debug("ProductServiceImpl.fetchImeisList ends");

        return res;
    }

    @Override
    public List<String> fetchProducersList() {
        logger.debug("ProductServiceImpl.fetchProducersList starts");

        List<Product> list = this.find(null);
        List<String> res = new ArrayList<String>();

        for (Product p : list) {

        }

        logger.debug("found {} elements", res.size());

        return res;
    }

    @Override
    public List<String> fetchModelsList() {
        logger.debug("ProductServiceImpl.fetchModelsList starts");

        List<Product> list = this.find(null);
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

    @Transactional
    @SuppressWarnings("unchecked")
    public List<Product> find(DeliveryFilterCriteria filters) {

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
    
	@Override
	public Product findByIMEI(String imei) {
	
	    logger.debug("ProductsDaoImpl.findByIMEI starts");
	    
        Product lst = (Product) 
        		getEntityManager()
        		.createQuery(
        		"select e from Product e " +
        		"where e.imei in (?1)")
				.setParameter(1, imei)
				.getSingleResult();
        
        
        return lst;
	}

	@Override
	public void removeByDeliveryId(Delivery delvieryToDelete) {

	    logger.debug("ProductsDaoImpl.removeByDeliveryId starts");
	    
		getEntityManager()
			.createQuery(
			"delete from Product e " +
			"where e.delivery = :delivery")
			.setParameter("delivery", delvieryToDelete)
			.executeUpdate();
    
	    logger.debug("ProductsDaoImpl.removeByDeliveryId ends");
	}

    @Override
    public boolean checkIfProductsAreAvailable(Collection<Long> productsIds) {

        logger.info("ProductsDaoImpl.checkIfProductsAreAvailable starts");

        if (logger.isDebugEnabled()) {
            logger.debug("params : [ productsIds : {} ]", productsIds);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) as productscount from Product p where p.id in (:ids) and p.sale is null");
        Query query = getEntityManager().createQuery(sb.toString());

        query.setParameter("ids", productsIds);

        List<Object[]> res = query.getResultList();
        Long count = (Long) res.get(0)[0];

        return count.equals(productsIds.size());
    }

    @Override
    public boolean checkIfProductIsAvailable(Long productId) {

        logger.info("ProductsDaoImpl.checkIfProductIsAvailable starts");

        if (logger.isDebugEnabled()) {
            logger.debug("params : [ productId : {} ]", productId);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) as productscount from Product p where p.id in (:ids) and p.sale is null");
        Query query = getEntityManager().createQuery(sb.toString());

        query.setParameter("ids", productId);

        List<Long> res = query.getResultList();
        Long count = res.get(0);

        return count.equals(1L);
    }

    @Override
    public boolean checkIfProductIsAssignedToSale(Long productId, Long saleId) {
        logger.info("ProductsDaoImpl.checkIfProductIsAvailable starts");

        if (logger.isDebugEnabled()) {
            logger.debug("params : [ productId : {} ]", productId);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(" select count(*) as productscount from Product p join p.sale s where p.id = :productId and s.id = :saleId ");
        Query query = getEntityManager().createQuery(sb.toString());

        query.setParameter("productId", productId);
        query.setParameter("saleId", saleId);

        List<Long> res = query.getResultList();
        Long count = res.get(0);

        return count.equals(1L);
    }

    @Override
    public List<String> fetchColorsList() {
        logger.info("ProductsDaoImpl.fetchColorsList starts");

        StringBuilder sb = new StringBuilder();
        sb.append(" select distinct color from Product p ");
        Query query = getEntityManager().createQuery(sb.toString());

        List<String> lst = (List<String>) query.getResultList();

        return lst;
    }

    @Override
    public Long countByCriteria(ProductFilterCriteria query) {

        StringBuilder sb = new StringBuilder();
        sb.append(" select count(distinct p.id) from Product p ");
        sb.append(" join p.delivery d ");
        sb.append(" join p.store st ");
        sb.append(" join p.model m ");
        sb.append(" join m.producer pr ");
        sb.append(" left join p.sale sa ");
        sb.append(" where 1=1 ");

        if (query.getImei() != null && query.getImei().length() > 0) {
            sb.append("and p.imei = :imei ");
        }

        if (query.getProducer() != null && query.getProducer().length() > 0) {
            sb.append("and pr.label = :producer ");
        }

        if (query.getModel() != null && query.getModel().length() > 0) {
            sb.append("and m.label = :model ");
        }

        if (query.getColor() != null && query.getColor().length() > 0) {
            sb.append("and p.color = :color ");
        }

        if (query.getStoreId() != null && query.getStoreId() > 0) {
            sb.append("and st.id = :storeId ");
        }

        if (isNotNull(query.getDeliveryDateStart())) {
            sb.append("and d.dateIn >= :deliveryDateStart ");
        }

        if (isNotNull(query.getDeliveryDateEnd())) {
            sb.append("and d.dateIn <= :deliveryDateEnd ");
        }

        if (isNotNull(query.getSaleDateStart())) {
            sb.append("and sa.dateIn >= :saleDateStart ");
        }

        if (isNotNull(query.getSaleDateEnd())) {
            sb.append("and sa.dateIn <= :saleDateEnd ");
        }

        if (isNotNull(query.getStatus())) {
            if (query.getStatus() == ProductStatus.IN_STORE) {
                sb.append("and sa.id is null ");
            } else if (query.getStatus() == ProductStatus.SOLD) {
                sb.append("and sa.id is not null ");
            }
        }

        if (isNotEmpty(query.getDeliveriesIds())) {
            sb.append("and d.id IN (:deliveriesIds) ");
        }

        if (isNotEmpty(query.getSalesIds())) {
            sb.append("and d.id IN (:salesIds) ");
        }

        if (isNotEmpty(query.getProductIds())) {
            sb.append("and p.id in (:productIds) ");
        }

        List<Product> res = null;

        Query jpaQuery = getEntityManager()
                .createQuery(sb.toString());

        if (query.getImei() != null && query.getImei().length() > 0) {
            jpaQuery.setParameter("imei", query.getImei());
        }

        if (query.getProducer() != null && query.getProducer().length() > 0) {
            jpaQuery.setParameter("producer", query.getProducer());
        }

        if (isNotEmpty(query.getModel())) {
            jpaQuery.setParameter("model", query.getModel());
        }

        if (isNotEmpty(query.getColor())) {
            jpaQuery.setParameter("color", query.getColor());
        }

        if (query.getStoreId() != null && query.getStoreId() > 0) {
            jpaQuery.setParameter("storeId", query.getStoreId());
        }

        if (isNotNull(query.getDeliveryDateStart())) {
            Timestamp deliveryDateStartTmp = new Timestamp(query.getDeliveryDateStart().getTime());
            jpaQuery.setParameter("deliveryDateStart", deliveryDateStartTmp);
        }

        if (isNotNull(query.getDeliveryDateEnd())) {
            Timestamp deliveryDateEndTmp = new Timestamp(query.getDeliveryDateEnd().getTime());
            jpaQuery.setParameter("deliveryDateEnd", deliveryDateEndTmp);
        }

        if (isNotNull(query.getSaleDateStart())) {
            Timestamp saleDateStartTmp = new Timestamp(query.getSaleDateStart().getTime());
            jpaQuery.setParameter("saleDateStart", saleDateStartTmp);
        }

        if (isNotNull(query.getDeliveryDateEnd())) {
            Timestamp saleDateEndTmp = new Timestamp(query.getSaleDateEnd().getTime());
            jpaQuery.setParameter("saleDateEnd", saleDateEndTmp);
        }

        if (isNotEmpty(query.getProductIds())) {
            jpaQuery.setParameter("productIds", query.getProductIds());
        }

        if (isNotEmpty(query.getDeliveriesIds())) {
            jpaQuery.setParameter("deliveriesIds", query.getDeliveriesIds());
        }

        if (isNotEmpty(query.getSalesIds())) {
            jpaQuery.setParameter("salesIds", query.getSalesIds());
        }


        // TODO improve it later
        List<Long> lst = (List<Long>) jpaQuery.getResultList();
        long count = 0;

        for (Long l : lst) {
            count += l;
        }

        return count;
    }
}
