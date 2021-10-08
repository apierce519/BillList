/**
 * @author Andrew Pierce - ajpierce1
 * CIS175 - Fall 2021
 * Oct 6, 2021
 */
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.BillList;

/**
 * @author Andrew Pierce - ajpierce1
 */
public class BillListHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("BillList");

	/**
	 * 
	 */
	public BillListHelper() {
		// TODO Auto-generated constructor stub
	}

	public void insertNewBillList(BillList bl) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(bl);
		em.getTransaction().commit();
		em.close();
	}

	@SuppressWarnings("unchecked")
	public List<BillList> getLists() {
		EntityManager em = emfactory.createEntityManager();
		List<BillList> allLists = em.createQuery("SELECT b FROM BillList b").getResultList();
		return allLists;
	}

	public void deleteList(BillList blToDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<BillList> typedQuery = em.createQuery("SELECT list FROM BillList list WHERE list.id = :selectedId",
				BillList.class);

		typedQuery.setParameter("selectedId", blToDelete.getId());
		typedQuery.setMaxResults(1);
		BillList foundList = typedQuery.getSingleResult();

		em.remove(foundList);
		em.getTransaction().commit();
		em.close();
	}

	public BillList searchForBillListById(Integer tempId) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();

		BillList foundList = em.find(BillList.class, tempId);
		em.close();

		return foundList;
	}

	public void updateBillList(BillList blToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();

		em.merge(blToEdit);
		em.getTransaction().commit();
		em.close();
	}
}
