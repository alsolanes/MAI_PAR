package operator;

import constants.PreconditionName;
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
	}
	
	private void init_pre() {
		Predicate rob_loc = new predicate.RobotLocation(o);
		this.list_preconditions.put(PreconditionName.ROBOTLOCATION, rob_loc);
		
		Predicate dirt = new predicate.Dirty(o);
		this.list_preconditions.put(PreconditionName.DIRTY, dirt);
		
		Predicate empt = new predicate.Empty(o);
		this.list_preconditions.put(PreconditionName.EMPTY, empt);
	}

	private void init_add() {
		Predicate cln = new predicate.Clean(o);
		this.list_add.put(PreconditionName.CLEAN, cln);		
	}
	
	private void init_del() {
		Predicate drt = new predicate.Dirty(o);
		this.list_delete.put(PreconditionName.DIRTY, drt);
	}

	

	/**
	 * @return the o
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


}
