package operator;

import item.Office;
import predicate.Predicate;

public class CleanOffice extends Operator{
	
	private Office o;

	/**
	 * Robot cleans office o
	 * Preconditions: 		robot-location(o), dirty(o), empty(o)
	 * Add conditions: 		clean(o)
	 * Delete conditions: 	dirty(o)
	 * @param o
	 */
	public CleanOffice(Office o){
		super();
		this.o = o;
		init_pre();
		init_add();
		init_del();
		super.name = "CLEAN-OFFICE";
	}
	
	private void init_pre() {
		Predicate rob_loc = new predicate.RobotLocation(o);
		this.list_preconditions.add(rob_loc);
		
		Predicate dirt = new predicate.Dirty(o);
		this.list_preconditions.add(dirt);
		
		Predicate empt = new predicate.Empty(o);
		this.list_preconditions.add(empt);
	}

	private void init_add() {
		Predicate cln = new predicate.Clean(o);
		this.list_add.add(cln);		
	}
	
	private void init_del() {
		Predicate drt = new predicate.Dirty(o);
		this.list_delete.add(drt);
	}

	

	/**
	 * @return the office to be cleaned
	 */
	public Office getO() {
		return o;
	}

	/**
	 * @param o the o to set
	 */
	public void setO(Office o) {
		this.o = o;
	}

	public String toString(){
		return "Clean-office("+o.id+")";
	}
	
	public String getName(){
		return super.name;
	}


}
