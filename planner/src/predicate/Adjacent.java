package predicate;

import constants.PreconditionName;
import item.Office;

/**
 * office o1 and o2 are horizontally or vertically adjacent
 * @author Aleix
 *
 */
public class Adjacent extends Predicate {

	Office o1, o2;
	
	public Adjacent(Office o1, Office o2){
		super();
		this.o1 = o1;
		this.o2 = o2;
		this.name = PreconditionName.ADJACENT;
	}
	
	public String toString() {
		String of1 = "null";
		String of2 = "null";
		if(o1!=null)
			of1=o1.id;
		if(o2!=null)
			of2=o2.id;
		return "Adjacent("+of1+","+of2+")";
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
	
}
