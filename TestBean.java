package edu.pjwstk.sri.lab2.test;

import edu.pjwstk.sri.lab2.dao.CategoryDao;
import edu.pjwstk.sri.lab2.dao.ProductDao;
import edu.pjwstk.sri.lab2.model.Category;
import edu.pjwstk.sri.lab2.model.Product;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("testBean")
@RequestScoped
public class TestBean implements Serializable {

	@Inject
	private CategoryDao categoryDao;

	@Inject
	private ProductDao productDao;

	private List<Product> products;
	
	public TestBean() {
	}
	
	public void test() {
		List<Category> categories = categoryDao.listAll();

		products = productDao.listAll(null, null);

		Product product = products.get(1);
		product.setStock(50);
		productDao.update(product);

	}
}
