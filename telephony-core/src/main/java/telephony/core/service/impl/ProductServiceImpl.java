package telephony.core.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ProductsDao;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductStatus;
import telephony.core.entity.jpa.Store;
import telephony.core.query.filter.ProductFilterCriteria;
import telephony.core.service.ProductService;
import telephony.core.service.SessionService;
import telephony.core.service.dto.Session;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

/**
 * Products management service.
 */
public class ProductServiceImpl extends AbstractBasicService<Product> implements
		ProductService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private ProductsDao productsDao;
	
	@Inject
	private SessionService sessionService;

	@Override
	@Transactional
	public List<String> fetchAllImeiInUse(Session session) 
			throws SessionServiceException {
		logger.debug("ProductServiceImpl.fetchAllImeiInUse starts");
		
		sessionService.validate(session);

		List<String> res = productsDao.fetchImeisList();

		logger.debug("found {} elements", res.size());

		return res;
	}

	@Override
	@Transactional
	public List<String> fetchAllProducersInUse(Session session) {
		logger.debug("ProductServiceImpl.fetchAllProducers starts");

		List<String> res = new ArrayList<String>();
		List<Product> products = productsDao.find();

		for (Product p : products) {
			if (!res.contains(p.getModel().getProducer().getLabel())) {
				res.add(p.getModel().getProducer().getLabel());
			}
		}

		logger.debug("found {} elements ", res.size());

		return res;
	}

	@Override
	@Transactional
	public List<String> fetchAllModels(Session session) 
			throws SessionServiceException {
		logger.debug("ProductServiceImpl.fetchAllModels starts");
		
		sessionService.validate(session);

		List<String> res = new ArrayList<String>();
		List<Product> products = productsDao.find();

		for (Product p : products) {
			if (!res.contains(p.getModel().getLabel())) {
				res.add(p.getModel().getLabel());
			}
		}

		logger.debug("found {} elements ", res.size());

		return res;
	}

	@Override
	@Transactional
	public List<String> fetchAllColors(Session session) {
		logger.debug("ProductServiceImpl.fetchAllModels starts");

		List<String> res = new ArrayList<String>();
		List<Product> products = productsDao.find();

		for (Product p : products) {
			if (!res.contains(p.getColor())) {
				res.add(p.getColor());
			}
		}

		logger.debug("found {} elements ", res.size());

		return res;
	}


	@Transactional
	@Override
	public List<Product> fetchAllProducts(
			Session session, Long storeId, ProductStatus productStatus) 
			throws SessionServiceException {
		
		sessionService.validate(session);

		logger.debug("ProductServiceImpl.fetchAllProducts starts ");
		logger.debug("params : [ session : {}, storeId : {} , productStatus : {} ] ",
				new Object[] {session, storeId, productStatus});

		List<Product> lst = productsDao.findByStoreAndStatus(storeId, productStatus);
		
		logger.debug("ProductServiceImpl.fetchAllProducts ends");

		return lst;
	}

	@Override
	@Transactional
	public void moveProducts(Session session, Store store, 
			List<Product> products) {
		
		logger.debug("ProductServiceImpl.moveProducts starts ");
		logger.debug("params : [ session : {}, {}, storeId : {} , products : {}] ",
				new Object[] {session, store, products});

		for (Product p : products) {
			p.setStore(store);
			productsDao.save(p);
		}

		logger.debug("ProductServiceImpl.fetchAllProducts ends");
	}

	@Override
	@Transactional
	public Product fetchProductByImeiAndStoreId(
			Session session, String imei, Long storeId) 
			throws SessionServiceException {
		
		logger.debug("ProductServiceImpl.fetchProductByImeiAndStoreId starts");
		
		sessionService.validate(session);

		Product product = productsDao.findByImeiAndStoreId(imei, storeId);

		return product;
	}

	@Override
	@Transactional
	public List<Product> findByCriteria(
			Session session, ProductFilterCriteria parameterObject) 
			throws SessionServiceException {
		
		logger.debug("ProductServiceImpl.fetchAllProductsByCriteria starts ");
		Object[] params = new Object[] { parameterObject.getImei(), parameterObject.getProducer(), 
				parameterObject.getModel(), parameterObject.getColor(), 
				parameterObject.getStoreId(), parameterObject.getDeliveryDateStart(),
				parameterObject.getDeliveryDateEnd(), parameterObject.getStatus()};
		
		sessionService.validate(session);
		
		// TODO : log parameterObject instaed of each part
		logger.debug("params : [ imei : {} , producer : {} , model : {} , "
				+ "color : {} , storeId : {} , deliveryDateStart : {} , "
				+ "deliveryDateEnd : {}, productStatus : {} ] ", params);

		List<Product> result = productsDao.findByCriteria(parameterObject);

		logger.info("ProductServiceImpl.fetchAllProductsByCriteria ends");

		for (Product p : result) {
//			logger.info(" model : {} , producer : {} ", p.getModel(),
//					p.getProducer());
		}

		return result;
	}

	@Override
	@Transactional
	public long count(Session session) {

		return productsDao.count();
	}

	@Override
	@Transactional
	public List<Product> findByStore(Session session, Store store) 
			throws SessionServiceException {
		
		logger.info("findByStore starts");
		logger.info("params : [ session : {}, store : {} ]", session, store);
		
		sessionService.validate(session);
		
		return productsDao.findByStore(store);		
	}

	@Transactional
	@Override
	public List<Product> findByIMEIs(Session session, List<String> imeis) 
			throws SessionServiceException {

		logger.info("findByStore starts");
		logger.info("params : [ session : {}, store : {} ]", session, imeis);
		
		sessionService.validate(session);
		
		return productsDao.findByIMEIs(imeis);
	}

	@Transactional
	@Override
	public Product findById(Session session, long id) {
		
		logger.info("findById starts");
		logger.info("params : [ id : {} ]", id);
		
		return productsDao.findById(id);		
	}

	@Transactional
	@Override
	public Collection<Product> findById(Session session, Collection<Long> coll) {
		logger.info("findById starts");
		logger.info("params : [ numberOfIds : {} ]", coll.size());
		
		return productsDao.findByIds(coll);		
	}


	@Transactional
	@Override
	public Product update(Session session, Product product) {
		logger.info("update starts");
		logger.info("params : [ product : {} ]", product);
		
		return productsDao.saveOrUpdate(product);		
	}
	
	@Transactional
	@Override
	public Product findByIMEI(Session session, String imei) {
		logger.info("update starts");
		logger.info("params : [ imei: {} ]", imei);
		
		return productsDao.findByIMEI(imei);		
	}

	@Transactional
	@Override
	public Collection<Product> updateCollection(Session session, Collection<Product> coll) {
		logger.info("updateCollection starts");
		logger.info("params : [ numberOfProducts: {} ]", coll.size());
		
		return productsDao.saveOrUpdate(coll);		
	}

	@Transactional
	@Override
	public void remove(Session session, Product product) {
		logger.info("remove starts");
		logger.info("params : [ product: {} ]", product);
		
		productsDao.remove(product);		
	}

	@Transactional
	@Override
	public void removeCollection(Session session, Collection<Product> coll) {
		logger.info("removeCollection starts");
		logger.info("params : [ numberOfproduct: {} ]", coll.size());
		
		productsDao.remove(coll);		
	}

	@Transactional
	@Override
	public void removeCollectionByIds(Session session, Collection<Long> coll) {
		logger.info("removeCollection starts");
		logger.info("params : [ numberOfproduct: {} ]", coll.size());
		
		productsDao.removeByIds(coll);		
	}

	@Transactional
	@Override
	public void removeById(Session session, long id) {
		logger.info("remove starts");
		logger.info("params : [ id: {} ]", id);
		
		productsDao.removeById(id);		
	}

}
