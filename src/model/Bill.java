/**
 * @author Andrew Pierce - ajpierce1
 * CIS175 - Fall 2021
 * Sep 12, 2021
 */
package model;

import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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

@Table
/*
 * By default the table name will be the class name. Adding the 
 * (name="xyz") allows setting your own name.
 */


/**
 * @author Andrew Pierce - ajpierce1
 */
public class Bill {

	public Bill() {
		super();
	}

	public Bill(String billName, double billCost) {
		super();
		this.name = billName;
		this.cost = billCost;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	/*
	 * This annotation will instruct JPA to automatically generate the ID number
	 * for each instance.
	 */
	/*
	 * This annotation changes the column name for this field. 
	 */
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="cost")
	private double cost;

	static DecimalFormat df = new DecimalFormat("$#.00");



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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return id + ": " + name + ": " + df.format(cost);
	}

}
