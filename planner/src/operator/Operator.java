package operator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import predicate.Predicate;

public abstract class Operator {

	Map <String, Predicate> list_preconditions;
	Map <String, Predicate> list_add;
	Map <String, Predicate> list_delete;
	
	public Operator(){
		this.list_preconditions = new HashMap<String, Predicate>();
		this.list_add = new HashMap<String, Predicate>();
		this.list_delete = new HashMap<String, Predicate>();
	}
	
	/**
	 * sets all preconditions to a HashMap
	 * @param pred
	 */
	public Operator(Map<String, Predicate> preconditions, Map<String, Predicate> add, Map<String, Predicate> delete){
		this.list_preconditions = preconditions;
		this.list_add = add;
		this.list_delete = delete;
	}

	/**
	 * @return the list_preconditions
	 */
	public Map<String, Predicate> getList_preconditions() {
		return list_preconditions;
	}

	/**
	 * @param list_preconditions the list_preconditions to set
	 */
	public void setList_preconditions(Map<String, Predicate> list_preconditions) {
		this.list_preconditions = list_preconditions;
	}

	/**
	 * @return the list_add
	 */
	public Map<String, Predicate> getList_add() {
		return list_add;
	}

	/**
	 * @param list_add the list_add to set
	 */
	public void setList_add(Map<String, Predicate> list_add) {
		this.list_add = list_add;
	}

	/**
	 * @return the list_delete
	 */
	public Map<String, Predicate> getList_delete() {
		return list_delete;
	}

	/**
	 * @param list_delete the list_delete to set
	 */
	public void setList_delete(Map<String, Predicate> list_delete) {
		this.list_delete = list_delete;
	}
	
}
