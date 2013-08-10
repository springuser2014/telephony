package telephony.core.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ProductsDao;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductStatus;

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
     * asd.
     * @param userId asd.
     * @param productStatus asd.
     * @return asd.
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

	@Override
    @SuppressWarnings("unchecked")
    public List<Product> findByStoreId(Long storeId, ProductStatus productStatus) {
        logger.debug("findByStore starts");
        logger.debug("params : [ storeId : {} , productStatus : {} ]", storeId, productStatus);

        List<Product> res = null;

        if (productStatus.toString().equals(ProductStatus.IN_STORE.toString())) {

            res = getEntityManager().createQuery("select p from Product p "
                    + "left join p.delivery d "
                    + "left join p.store st "
                    + "left join p.sale sa "
                    + "where st.id = ?1 and sa.id is null")
                    .setParameter(1, storeId)
                    .getResultList();
        } else {
            res = getEntityManager().createQuery("select p from Product p "
                    + "left join p.delivery d "
                    + "left join p.store st "
                    + "left join p.sale sa "
                    + "where st.id = ?1 and sa.id is not null")
                    .setParameter(1, storeId)
                    .getResultList();
        }

        logger.debug("findByStore returns {} elements", res.size());

        return res;
    }

	// TOOD: refactor method below to use Query object or something
	/**
	 * asd.
	 * @param imei asd.
	 * @param producer asd.
	 * @param model asd.
	 * @param color asd.
	 * @param storeId asd.
	 * @param deliveryDateStart asd.
	 * @param deliveryDateEnd asd.
	 * @param productStatus asd.
	 * @return asd.
	 */
    @SuppressWarnings("unchecked")
    @Override
    public List<Product> findByCriteria(
        String imei, String producer, String model,
        String color, Long storeId, Date deliveryDateStart,
        Date deliveryDateEnd, ProductStatus productStatus) {

        StringBuilder sb = new StringBuilder();
        sb.append(" select p from Product p ");
        sb.append(" join fetch p.delivery d ");
        sb.append(" join fetch p.store st ");
        sb.append(" left join fetch p.sale sa ");
        sb.append(" where 1=1 ");

        if (imei != null && imei.length() > 0) {
            sb.append("and p.imei = :imei ");
        }

        if (producer != null && producer.length() > 0) {
            sb.append("and p.producer = :producer ");
        }

        if (model != null && model.length() > 0) {
            sb.append("and p.model = :model ");
        }

        if (color != null && color.length() > 0) {
            sb.append("and p.color = :color ");
        }

        if (storeId != null && storeId > 0) {
            sb.append("and st.id = :storeId ");
        }

        if (deliveryDateStart != null) {
            sb.append("and d.dateIn >= :deliveryDateStart ");
        }

        if (deliveryDateEnd != null) {
            sb.append("and d.dateIn <= :deliveryDateEnd ");
        }

        if (productStatus == ProductStatus.IN_STORE) {
            sb.append("and sa.id is null ");
        } else if (productStatus == ProductStatus.SOLD) {
            sb.append("and sa.id is not null ");
        }

        List<Product> res = null;

        Query query = getEntityManager().createQuery(sb.toString());

        if (imei != null && imei.length() > 0) {
            query.setParameter("imei", imei);
        }

        if (producer != null && producer.length() > 0) {
            query.setParameter("producer", producer);
        }

        if (model != null && model.length() > 0) {
            query.setParameter("model", model);
        }

        if (color != null && color.length() > 0) {
            query.setParameter("color", color);
        }

        if (storeId != null && storeId > 0) {
            query.setParameter("storeId", storeId);
        }

        if (deliveryDateStart != null) {
            Timestamp deliveryDateStartTmp = new Timestamp(deliveryDateStart.getTime());
            query.setParameter("deliveryDateStart", deliveryDateStartTmp);
        }

        if (deliveryDateEnd != null) {
            Timestamp deliveryDateEndTmp = new Timestamp(deliveryDateEnd.getTime());
            query.setParameter("deliveryDateEnd", deliveryDateEndTmp);
        }

        res = query.getResultList();

        logger.info(" size : {}", res.size());


        return res;
    }


    @Override
    public Product findByImei(String imei) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


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

    @Override
    public List<String> fetchProducersList() {
        logger.debug("ProductServiceImpl.fetchProducersList starts");

        List<Product> list = this.find();
        List<String> res = new ArrayList<String>();

        for (Product p : list) {
            if (!res.contains(p.getProducer())) {
                res.add(p.getProducer());
            }
        }

        logger.debug("found {} elements", res.size());

        return res;
    }


    @Override
    public List<String> fetchModelsList() {
        logger.debug("ProductServiceImpl.fetchModelsList starts");

        List<Product> list = this.find();
        List<String> res = new ArrayList<String>();

        for (Product p : list) {
            if (!res.contains(p.getModel())) {
                res.add(p.getModel());
            }
        }

        logger.debug("found {} elements", res.size());

        return res;
    }

    @Override
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
}
