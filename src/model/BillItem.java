/**
 * @author Andrew Pierce - ajpierce1
 * CIS175 - Fall 2021
 * Sep 12, 2021
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * These import statements are generated/suggested by Eclipse
 * by adding the annotations below.
 */

@Entity
/*
 * Entity = A class that should be persisted.
 * Annotation telling the persistence provider this object should be "persisted"
 * in the database. Each Entity instance will be its own table row.
 * In JPA, each entity has its own database table.
 */

@Table(name="Bills")
/*
 * By default the table name will be the class name. Adding the 
 * (name="xyz") allows setting your own name.
 */


/**
 * @author Andrew Pierce - ajpierce1
 */
public class BillItem {

	public BillItem() {
		super();
	}

	public BillItem(String billName, double billCost) {
		super();
		this.billName = billName;
		this.billCost = billCost;

	}

	/*
	 * 09/14/2021 changing it this deep in is going to be a pain
	 * but next time, you don't need billName/billCost as a variable name.
	 * It's redundant when you are going to be calling things like bill.name.
	 */
	@Id
	/*
	 * This annotation 
	 */
	@GeneratedValue
	/*
	 * This annotation will instruct JPA to automatically generate the ID number
	 * for each instance.
	 */
	@Column(name="ID")
	/*
	 * This annotation changes the column name for this field. 
	 */
	private int id;
	@Column(name="name")
	private String billName;
	@Column(name="cost")
	private double billCost;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the billName
	 */
	public String getBillName() {
		return billName;
	}

	/**
	 * @param billName the billName to set
	 */
	public void setBillName(String billName) {
		this.billName = billName;
	}

	/**
	 * @return the billCost
	 */
	public double getBillCost() {
		return billCost;
	}

	/**
	 * @param billCost the billCost to set
	 */
	public void setBillCost(double billCost) {
		this.billCost = billCost;
	}

	@Override
	public String toString() {
		return id + ": " + billName + ": $" + billCost;
	}

}
