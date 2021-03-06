package operator;

import java.util.ArrayList;

import predicate.Predicate;

public abstract class Operator {

	ArrayList<Predicate> list_preconditions;
	ArrayList<Predicate> list_add;
	ArrayList<Predicate> list_delete;
	String name;
	
	public Operator(){
		this.list_preconditions = new ArrayList<Predicate>();
		this.list_add = new ArrayList<Predicate>();
		this.list_delete = new ArrayList<Predicate>();
	}
	
	/**
	 * sets all preconditions to a HashMap
	 * @param pred
	 */
	public Operator(ArrayList<Predicate> preconditions, ArrayList<Predicate> add, ArrayList<Predicate> delete){
		this.list_preconditions = preconditions;
		this.list_add = add;
		this.list_delete = delete;
	}

	/**
	 * @return the list_preconditions
	 */
	public ArrayList<Predicate> getList_preconditions() {
		return list_preconditions;
	}

	/**
	 * @param list_preconditions the list_preconditions to set
	 */
	public void setList_preconditions(ArrayList<Predicate> list_preconditions) {
		this.list_preconditions = list_preconditions;
	}

	/**
	 * @return the list_add
	 */
	public ArrayList<Predicate> getList_add() {
		return list_add;
	}

	/**
	 * @param list_add the list_add to set
	 */
	public void setList_add(ArrayList<Predicate> list_add) {
		this.list_add = list_add;
	}

	/**
	 * @return the list_delete
	 */
	public ArrayList<Predicate> getList_delete() {
		return list_delete;
	}

	/**
	 * @param list_delete the list_delete to set
	 */
	public void setList_delete(ArrayList<Predicate> list_delete) {
		this.list_delete = list_delete;
	}
	
	public abstract String getName();
	public abstract boolean isPartial();
	
}
