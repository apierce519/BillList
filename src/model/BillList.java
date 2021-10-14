/**
 * @author Andrew Pierce - ajpierce1
 * CIS175 - Fall 2021
 * Oct 6, 2021
 */
package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
/**
 * @author Andrew Pierce - ajpierce1
 */
public class BillList {

	@Id
	@GeneratedValue
	private int id;
	private String listName;
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Bill> listOfBills;
	
	/**
	 * 
	 */
	public BillList() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param listName
	 * @param listOfBills
	 */
	public BillList(int id, String listName, List<Bill> listOfBills) {
		super();
		this.id = id;
		this.listName = listName;
		this.listOfBills = listOfBills;
	}

	/**
	 * @param id
	 * @param listName
	 */
	public BillList(int id, String listName) {
		super();
		this.id = id;
		this.listName = listName;
	}

	/**
	 * @param billListName
	 */
	public BillList(String billListName) {
		// TODO Auto-generated constructor stub
		this.listName = billListName;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the listName
	 */
	public String getListName() {
		return listName;
	}

	/**
	 * @return the listOfBills
	 */
	public List<Bill> getListOfBills() {
		return listOfBills;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param listName the listName to set
	 */
	public void setListName(String listName) {
		this.listName = listName;
	}

	/**
	 * @param listOfBills the listOfBills to set
	 */
	public void setListOfBills(List<Bill> listOfBills) {
		this.listOfBills = listOfBills;
	}

	@Override
	public String toString() {
		return "BillList [id=" + id + ", listName=" + listName + ", listOfBills=" + listOfBills + "]";
	}
	
	
	

}
