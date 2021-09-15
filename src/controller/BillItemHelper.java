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

import model.BillItem;

/**
 * @author Andrew Pierce - ajpierce1
 */
public class BillItemHelper {
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
	public void addBill(BillItem newBill) {
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
	public List<BillItem> showAllBills() {
		EntityManager em = emFactory.createEntityManager();
		List<BillItem> allbills = em.createQuery("SELECT i FROM BillItem i").getResultList();
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
	public List<BillItem> searchByName(String name) {
		// TODO Auto-generated method stub
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<BillItem> typedQuery = em.createQuery("select bill from BillItem bill where bill.billName = :selectedBillName", BillItem.class);
		typedQuery.setParameter("selectedBillName", name);
		List<BillItem> foundBills = typedQuery.getResultList();

		em.close();
		return foundBills;
	}

	/**
	 * @param cost
	 * @return
	 * 
	 */
	public List<BillItem> searchByCost(double cost) {
		// TODO Auto-generated method stub
		System.out.println("Searching by cost");
		return null;
	}

	/**
	 * @param idSearchEntry
	 */
	public BillItem searchBillsById(int idSearchEntry) {
		// TODO Auto-generated method stub
		EntityManager em = emFactory.createEntityManager();
		em.getTransaction().begin();
		BillItem found = em.find(BillItem.class,idSearchEntry);
		em.close();
		return found;
	}

	/**
	 * @param selectedEntry
	 */
	public void editBillEntry(BillItem selectedEntry) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param billToDelete
	 */
	public void deleteBillEntry(BillItem billToDelete) {
		// TODO Auto-generated method stub
		System.out.println("Entry Deleted.");
	}

}
