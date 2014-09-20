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
import telephony.core.query.filter.RoleFilterCriteriaBuilder;
import telephony.core.service.ProductService;
import telephony.core.service.SessionService;
import telephony.core.service.dto.*;
import telephony.core.service.exception.SessionServiceException;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

/**
 * Products management service.
 */
public class ProductServiceImpl 
extends AbstractBasicService<Product> 
implements ProductService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private ProductsDao productsDao;
	
	@Inject
	private SessionService sessionService;

	@Override
	@Transactional
	public List<String> fetchAllImeiInUse(SessionDto session) 
			throws SessionServiceException {
		logger.debug("ProductServiceImpl.fetchAllImeiInUse starts");
		
		sessionService.validate(session);

		List<String> res = productsDao.fetchImeisList();

		logger.debug("found {} elements", res.size());

		return res;
	}

	@Override
	@Transactional
	public List<String> fetchAllProducersInUse(SessionDto session) {
		logger.debug("ProductServiceImpl.fetchAllProducers starts");

		List<String> res = new ArrayList<String>();
		ProductFilterCriteria filters = ProductFilterCriteria.create();
		List<Product> products = productsDao.findByCriteria(filters);

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
	public List<String> fetchAllModels(SessionDto session) 
			throws SessionServiceException {
		logger.debug("ProductServiceImpl.fetchAllModels starts");
		
		sessionService.validate(session);

		List<String> res = new ArrayList<String>();
		
		ProductFilterCriteria filters = ProductFilterCriteria.create();
		List<Product> products = productsDao.findByCriteria(filters);

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
	public List<String> fetchAllColors(SessionDto session) {
		logger.debug("ProductServiceImpl.fetchAllModels starts");

		List<String> res = new ArrayList<String>();
		ProductFilterCriteria filters = ProductFilterCriteria.create();
		List<Product> products = productsDao.findByCriteria(filters);

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
			SessionDto session, Long storeId, ProductStatus productStatus) 
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
	public void moveProducts(SessionDto session, Store store, 
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
			SessionDto session, String imei, Long storeId) 
			throws SessionServiceException {
		
		logger.debug("ProductServiceImpl.fetchProductByImeiAndStoreId starts");
		
		sessionService.validate(session);

		Product product = productsDao.findByImeiAndStoreId(imei, storeId);

		return product;
	}

	@Override
	@Transactional
	public List<Product> findByCriteria(
			SessionDto session, ProductFilterCriteria parameterObject) 
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
	public long count(SessionDto session) {

		return productsDao.count();
	}

	@Override
	@Transactional
	public List<Product> findByStore(SessionDto session, Store store) 
			throws SessionServiceException {
		
		logger.info("findByStore starts");
		logger.info("params : [ session : {}, store : {} ]", session, store);
		
		sessionService.validate(session);
		
		return productsDao.findByStore(store);		
	}

	@Transactional
	@Override
	public List<Product> findByIMEIs(SessionDto session, List<String> imeis) 
			throws SessionServiceException {

		logger.info("findByStore starts");
		logger.info("params : [ session : {}, store : {} ]", session, imeis);
		
		sessionService.validate(session);
		
		return productsDao.findByIMEIs(imeis);
	}

	@Transactional
	@Override
	public Product findById(SessionDto session, long id) {
		
		logger.info("findById starts");
		logger.info("params : [ id : {} ]", id);
		
		return productsDao.findById(id);		
	}

	@Transactional
	@Override
	public Collection<Product> findById(SessionDto session, Collection<Long> coll) {
		logger.info("findById starts");
		logger.info("params : [ numberOfIds : {} ]", coll.size());
		
		return productsDao.findByIds(coll);		
	}


	@Transactional
	@Override
	public Product update(SessionDto session, Product product) {
		logger.info("update starts");
		logger.info("params : [ product : {} ]", product);
		
		return productsDao.saveOrUpdate(product);		
	}
	
	@Transactional
	@Override
	public Product findByIMEI(SessionDto session, String imei) {
		logger.info("update starts");
		logger.info("params : [ imei: {} ]", imei);
		
		return productsDao.findByIMEI(imei);		
	}

	@Transactional
	@Override
	public Collection<Product> updateCollection(SessionDto session, Collection<Product> coll) {
		logger.info("updateCollection starts");
		logger.info("params : [ numberOfProducts: {} ]", coll.size());
		
		return productsDao.saveOrUpdate(coll);		
	}

	@Transactional
	@Override
	public void remove(SessionDto session, Product product) {
		logger.info("remove starts");
		logger.info("params : [ product: {} ]", product);
		
		productsDao.remove(product);		
	}

	@Transactional
	@Override
	public void removeCollection(SessionDto session, Collection<Product> coll) {
		logger.info("removeCollection starts");
		logger.info("params : [ numberOfproduct: {} ]", coll.size());
		
		productsDao.remove(coll);		
	}

	@Transactional
	@Override
	public void removeCollectionByIds(SessionDto session, Collection<Long> coll) {
		logger.info("removeCollection starts");
		logger.info("params : [ numberOfproduct: {} ]", coll.size());
		
		productsDao.removeByIds(coll);		
	}

	@Transactional
	@Override
	public void removeById(SessionDto session, long id) {
		logger.info("remove starts");
		logger.info("params : [ id: {} ]", id);
		
		productsDao.removeById(id);		
	}

	@Transactional
	@Override
	public ProductFetchResponseDto find(ProductFetchRequestDto req)
		throws SessionServiceException 
	{

		logger.debug("ProductServiceImpl.find starts ");
		
		SessionDto session = SessionDto.create()
				.setUsername(req.getUsername())
				.setSessionId(req.getSessionId());
		
		ProductFilterCriteria parameterObject = req.getFiltersCriteria();
		
		
		Object[] params = new Object[] { parameterObject.getImei(), parameterObject.getProducer(), 
				parameterObject.getModel(), parameterObject.getColor(), 
				parameterObject.getStoreId(), parameterObject.getDeliveryDateStart(),
				parameterObject.getDeliveryDateEnd(), parameterObject.getStatus()};
		
		sessionService.validate(session);
		
		logger.debug("params : [ imei : {} , producer : {} , model : {} , "
				+ "color : {} , storeId : {} , deliveryDateStart : {} , "
				+ "deliveryDateEnd : {}, productStatus : {} ] ", params);

		List<Product> result = productsDao.findByCriteria(parameterObject);
		
		List<ProductSearchDto> lst = new ArrayList<ProductSearchDto>();
		
		for(Product p : result) {
					
			ProductSearchDto b = new ProductSearchDto();
			
			b.setColor(p.getColor());
			b.setDeliveryId(p.getDelivery().getId());
			b.setImei(p.getImei());
			b.setPriceIn(p.getPriceIn());
			b.setPrice(p.getCurrentPricing().getRate());
			b.setTax(p.getCurrentTax().getTax().getRate());
			b.setModel(p.getModel().getLabel());
			b.setProducer(p.getModel().getProducer().getLabel());
			
			if (p.getSale() != null) {
				b.setSaleId(p.getSale().getId());
			} else {
				b.setSaleId(null);
			}
			
			b.setId(p.getId());
			
			lst.add(b);
		}

		logger.info("ProductServiceImpl.find ends");

		ProductFetchResponseDto resp = new ProductFetchResponseDto();
		resp.setProducts(lst);
		
		return resp;
	}

}
