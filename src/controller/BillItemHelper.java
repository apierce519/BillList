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

import model.BillItem;

/**
 * @author Andrew Pierce - ajpierce1
 */
public class BillItemHelper {
	/*
	 * EMF is used to construct new EM (which I don't believe have their own constructors). 
	 * The EM is used to add, delete, or edit a set
	 * of entities defined by the persistence unit 
	 * (persistence.xml where model.BillItem was added)
	 */
	EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("BillList");

	public BillItemHelper() {

	}

	/**
	 * @param newBit
	 */
	public void addBill(BillItem newBit) {
		// TODO Auto-generated method stub
		System.out.println("Bill added");
		
	}
	/*
	 * efFactory.createEntityManager() method is building a new EM
	 */
	@SuppressWarnings("unchecked")
	public List<BillItem> showAllBills() {
		EntityManager em = emFactory.createEntityManager();
		List<BillItem> allbills = em.createQuery("SELECT i FROM BillItem i").getResultList();
		return allbills;
		
	}

	/**
	 * @param source 
	 * @return 
	 * 
	 */
	public List<BillItem> searchBySource(String source) {
		// TODO Auto-generated method stub
		System.out.println("Searching by source");
		return null;
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
}
