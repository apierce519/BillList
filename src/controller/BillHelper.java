/**
 * @author Andrew Pierce - ajpierce1
 * CIS175 - Fall 2021
 * Sep 12, 2021
 */
package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Bill;

/**
 * @author Andrew Pierce - ajpierce1
 */
public class BillHelper {
	/*
	 * EMF is used to construct new EM (which I don't believe have their own
	 * constructors). The EM is used to add, delete, or edit a set of entities
	 * defined by the persistence unit (persistence.xml where model.BillItem was
	 * added)
	 */
	EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("BillList");

	/**
	 * @param newBit getTransaction() is the method that accesses or updates data on
	 *               the the database. begin() method instructs EM to do everything
	 *               following. persist() method makes the instance managed and
	 *               persistent. commit() method writes the changes to the database.
	 */
	public void addBill(Bill newBill) {
		// TODO Auto-generated method stub
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(newBill);
		em.getTransaction().commit();
		em.close();
	}

	/*
	 * emFactory.createEntityManager() method is building a new EM createQuery
	 * method is building JPQL query, JPQL query because it is pulling objects, not
	 * table data?
	 */
	@SuppressWarnings("unchecked")
	public List<Bill> showAllBills() {
		EntityManager em = emFactory.createEntityManager();
		List<Bill> allbills = em.createQuery("SELECT i FROM Bill i").getResultList();
		return allbills;

	}

	/**
	 * @param source
	 * @return I'm trying to imagine this in a SQL interface, with each line in the
	 *         query as a line equal like I would write in SQL, with some references
	 *         that JPA uses to plug in necessary information. createQuery() - makes
	 *         a "TypedQuery", which is an interface for executing queries.
	 *         setParameter() - Tells the query what it is I'm looking for for
	 *         whichever section. getResultList() - Creates a new List of BillItems
	 *         from the TypedQuery's results.
	 */
	public List<Bill> searchByName(String name) {
		// TODO Auto-generated method stub
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Bill> typedQuery = em.createQuery("select bill from Bill bill where bill.name = :selectedBillName", Bill.class);
		typedQuery.setParameter("selectedBillName", name);
		List<Bill> foundBills = typedQuery.getResultList();
		em.close();
		return foundBills;
	}

	/**
	 * @param cost
	 * @return
	 * 
	 */
	public List<Bill> searchByCost(double cost) {
		// TODO Auto-generated method stub
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Bill> typedQuery =em.createQuery("select bi from Bill bi where bi.cost >= :selectedCost",Bill.class);
		typedQuery.setParameter("selectedCost", cost);
		List<Bill> foundBills = typedQuery.getResultList();
		em.close();
		return foundBills;
	}

	/**
	 * @param idSearchEntry
	 */
	public Bill searchBillsById(int idSearchEntry) {
		// TODO Auto-generated method stub
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		Bill found = em.find(Bill.class,idSearchEntry);
		em.close();
		return found;
	}

	/**
	 * @param selectedEntry
	 * update method.
	 * in the merge method, 
	 */
	public void editBillEntry(Bill selectedEntry) {
		// TODO Auto-generated method stub
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(selectedEntry);
		em.getTransaction().commit();
		em.close();
		

	}

	/**
	 * @param billToDelete
	 */
	public void deleteBillEntry(Bill billToDelete) {
		// TODO Auto-generated method stub
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Bill> typedQuery = em.createQuery("select bi from Bill bi where bi.name = :selectedName and bi.cost = :selectedCost",Bill.class);
		
		typedQuery.setParameter("selectedName", billToDelete.getName());
		typedQuery.setParameter("selectedCost", billToDelete.getCost());
		
		typedQuery.setMaxResults(1);
		
		Bill result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public void exitResourceClosing() {
		emFactory.close();
	}
}
