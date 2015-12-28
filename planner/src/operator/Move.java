package operator;

import constants.PreconditionName;
import item.Office;
import predicate.Predicate;

public class Move extends Operator{

	private Office o1, o2;
	/**
	 * Robot moves from o1 to o2
	 * Precondition: 		robot-location(o1), adjacent(o1,o2)
	 * Add condition: 		robot-location(o2)
	 * Delete condition: 	robot-location(o1)
	 * @param o1
	 * @param o2
	 */
	public Move(Office o1, Office o2){
		super();
		this.o1 = o1;
		this.o2 = o2;
		init_pre();
		init_add();
		init_del();
	}
	private void init_pre() {
		Predicate rob_loc = new predicate.RobotLocation(o1);
		this.list_preconditions.put(PreconditionName.ROBOTLOCATION, rob_loc);
		
		Predicate adj = new predicate.Adjacent(o1,o2);
		this.list_preconditions.put(PreconditionName.ADJACENT, adj);
		
	}
	private void init_add() {
		Predicate rob_loc = new predicate.RobotLocation(o2);
		this.list_preconditions.put(PreconditionName.ROBOTLOCATION, rob_loc);
		
	}
	private void init_del() {
		Predicate rob_loc = new predicate.RobotLocation(o1);
		this.list_preconditions.put(PreconditionName.ROBOTLOCATION, rob_loc);
		
	}
	
	/**
	 * @return the o1
	 */
	public Office getO1() {
		return o1;
	}
	/**
	 * @param o1 the o1 to set
	 */
	public void setO1(Office o1) {
		this.o1 = o1;
	}
	/**
	 * @return the o2
	 */
	public Office getO2() {
		return o2;
	}
	/**
	 * @param o2 the o2 to set
	 */
	public void setO2(Office o2) {
		this.o2 = o2;
	}
	public String toString(){
		return "Move("+o1.id+","+o2.id+")";
	}
	
}
