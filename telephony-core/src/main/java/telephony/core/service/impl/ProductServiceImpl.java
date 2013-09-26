package telephony.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ProductsDao;
import telephony.core.entity.jpa.Money;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductStatus;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.service.ProductQueryCriteria;
import telephony.core.service.ProductService;
import telephony.core.service.SessionService;
import telephony.core.service.bean.Session;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

/**
 * Products management service.
 * 
 * @author Paweł Henek <pawelhenek@gmail.com>
 * 
 */
public class ProductServiceImpl extends AbstractBasicService<Product> implements
		ProductService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private ProductsDao productsDao;
	
	@Inject
	private SessionService sessionService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<String> fetchAllImeiInUse(String username, String sessionId) {
		logger.debug("ProductServiceImpl.fetchAllImeiInUse starts");

		List<String> res = productsDao.fetchImeisList();

		logger.debug("found {} elements", res.size());

		return res;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<String> fetchAllProducers(String username, String sessionId) {
		logger.debug("ProductServiceImpl.fetchAllProducers starts");

		List<String> res = new ArrayList<String>();
		List<Product> products = productsDao.find();

		for (Product p : products) {
			if (!res.contains(p.getProducer())) {
				res.add(p.getProducer());
			}
		}

		logger.debug("found {} elements ", res.size());

		return res;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<String> fetchAllModels(String username, String sessionId) {
		logger.debug("ProductServiceImpl.fetchAllModels starts");

		List<String> res = new ArrayList<String>();
		List<Product> products = productsDao.find();

		for (Product p : products) {
			if (!res.contains(p.getModel())) {
				res.add(p.getModel());
			}
		}

		logger.debug("found {} elements ", res.size());

		return res;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<String> fetchAllColors(String username, String sessionId) {
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Product> fetchAllProducts(String username,
			String sessionId, final Long storeId, final ProductStatus productStatus) {

		logger.debug("ProductServiceImpl.fetchAllProducts starts ");
		logger.debug("params : [ storeId : {} , productStatus : {} ] ",
				storeId, productStatus);

		logger.debug("ProductServiceImpl.fetchAllProducts ends");

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void moveProducts(String username,
			String sessionId, final Store store, final List<Product> products, final User user) {
		logger.debug("ProductServiceImpl.moveProducts starts ");
		logger.debug(
				"params : [ storeId : {} , number of products: {} , userId : {}] ",
				new Object[] { store, products, user });

		for (Product p : products) {
			p.setStore(store);
			productsDao.save(p);
		}

		logger.debug("ProductServiceImpl.fetchAllProducts ends");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Product fetchProductByImeiAndStoreId(String username,
			String sessionId, final String imei, final Long storeId) {
		logger.debug("ProductServiceImpl.fetchProductByImeiAndStoreId starts");

		Product p = productsDao.findByImeiAndStoreId(imei, storeId);

		return p;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<Product> fetchAllProductsByCriteria(String username, String sessionId, ProductQueryCriteria parameterObject) {
		logger.debug("ProductServiceImpl.fetchAllProductsByCriteria starts ");
		Object[] params = new Object[] { parameterObject.getImei(), parameterObject.getProducer(), 
				parameterObject.getModel(), parameterObject.getColor(), 
				parameterObject.getStoreId(), parameterObject.getDeliveryDateStart(),
				parameterObject.getDeliveryDateEnd(), parameterObject.getStatus()};
		
		// TODO : log parameterObject instaed of each part
		logger.debug("params : [ imei : {} , producer : {} , model : {} , "
				+ "color : {} , storeId : {} , deliveryDateStart : {} , "
				+ "deliveryDateEnd : {}, productStatus : {} ] ", params);

		List<Product> result = productsDao.findByCriteria(parameterObject);

		logger.info("ProductServiceImpl.fetchAllProductsByCriteria ends");

		for (Product p : result) {
			logger.info(" model : {} , producer : {} ", p.getModel(),
					p.getProducer());
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public long count() {

		return productsDao.count();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<Product> findByStore(String username, String sessionId, Store store) 
			throws SessionServiceException {
		logger.info("findByStore starts");
		logger.info("params : [ username  : {} , sessionId : {}, store : {} ]",
				username, sessionId, store);
		
		Session session = Session.create(username, sessionId);
		sessionService.validate(session);
		
		return productsDao.findByStore(store);		
	}
}
