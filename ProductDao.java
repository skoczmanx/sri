package edu.pjwstk.sri.lab2.dao;

import java.util.List;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import edu.pjwstk.sri.lab2.model.Product;

/**
 * DAO for Product
 */
@Stateful
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProductDao {
	@PersistenceContext(unitName = "sri2-persistence-unit")
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void create(Product entity) {
		em.persist(entity);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteById(Long id) {
		Product entity = em.find(Product.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Product findById(Long id) {
		return em.find(Product.class, id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Product entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Product> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Product> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.category ORDER BY p.id",
						Product.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
