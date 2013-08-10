package telephony.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.dao.ProductsDao;
import telephony.core.entity.jpa.Money;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductStatus;
import telephony.core.entity.jpa.Store;
import telephony.core.entity.jpa.User;
import telephony.core.service.ProductService;

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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<String> fetchAllImeiInUse() {
		logger.debug("ProductServiceImpl.fetchAllImeiInUse starts");

		List<String> res = productsDao.fetchImeisList();

		logger.debug("found {} elements", res.size());

		return res;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<String> fetchAllProducers() {
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
	public final List<String> fetchAllModels() {
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
	public final List<String> fetchAllColors() {
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
	public final List<Product> fetchAllProducts(final Long storeId,
			final ProductStatus productStatus) {

		logger.debug("ProductServiceImpl.fetchAllProducts starts ");
		logger.debug("params : [ storeId : {} , productStatus : {} ] ",
				storeId, productStatus);

		List<Product> result = productsDao
				.findByStoreId(storeId, productStatus);

		logger.debug("ProductServiceImpl.fetchAllProducts ends");

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public final void moveProducts(final Store store,
			final List<Product> products, final User user) {
		logger.debug("ProductServiceImpl.moveProducts starts ");
		logger.debug(
				"params : [ storeId : {} , number of products: {} , userId : {}] ",
				new Object[] { store, products, user });

//		getEntityManager().getTransaction().begin();

		for (Product p : products) {
			p.setStore(store);
			productsDao.save(p);
		}

//		getEntityManager().getTransaction().commit();

		logger.debug("ProductServiceImpl.fetchAllProducts ends");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Product fetchProductByImeiAndStoreId(final String imei,
			final Long storeId) {
		logger.debug("ProductServiceImpl.fetchProductByImeiAndStoreId starts");

		Product p = productsDao.findByImeiAndStoreId(imei, storeId);

		return p;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public final List<Product> fetchAllProductsByCriteria(final String imei,
			final String producer, final String model, final String color,
			final Long storeId, final Date deliveryDateStart,
			final Date deliveryDateEnd, final ProductStatus status) {
		logger.debug("ProductServiceImpl.fetchAllProductsByCriteria starts ");
		Object[] params = new Object[] { imei, producer, model, color, storeId,
				deliveryDateStart, deliveryDateEnd, status };
		logger.debug("params : [ imei : {} , producer : {} , model : {} , "
				+ "color : {} , storeId : {} , deliveryDateStart : {} , "
				+ "deliveryDateEnd : {}, productStatus : {} ] ", params);

		List<Product> result = productsDao.findByCriteria(imei, producer,
				model, color, storeId, deliveryDateStart, deliveryDateEnd,
				status);

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
	public final void updateProducts(final List<Product> productsToUpdate,
			final List<Product> productsToDelete,
			final List<Product> productsToCancelTheSale, final User editor) {
		logger.debug("ProductServiceImpl.updateProducts starts");

		for (Product p : productsToUpdate) {
			for (Product p1 : productsToDelete) {
				if (p1.getId().equals(p.getId())) {
					productsToUpdate.remove(p);
				}
			}

			for (Product p2 : productsToCancelTheSale) {
				if (p2.getId().equals(p.getId())) {
					productsToUpdate.remove(p);
				}
			}

		}

		if (productsToUpdate.size() > 0) {
			productsDao.saveOrUpdate(productsToUpdate);
		}

		if (productsToDelete.size() > 0) {
			productsDao.remove(productsToDelete);
		}

		if (productsToCancelTheSale.size() > 0) {
			this.cancelProductsSale(productsToCancelTheSale, editor);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public final void cancelProductsSale(
			final List<Product> productsToCancelTheSale, final User editor) {
		logger.debug("ProductServiceImpl.cancelProductsSale starts");

		for (Product p : productsToCancelTheSale) {
			p.setSale(null);
			p.setPriceOut(new Money(0, 0));
		}

		productsDao.saveOrUpdate(productsToCancelTheSale);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void updateProducts(final List<Product> products,
			final User updatingUser) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() {

		return productsDao.count();
	}
}
