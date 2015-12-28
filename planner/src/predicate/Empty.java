package predicate;

import constants.PreconditionName;
import item.Office;

/**
 * There isn't any box in office o
 * @author Aleix
 *
 */
public class Empty extends Predicate {
	private Office o;

	public Empty(Office o){
		super();
		this.o = o;
		this.name = PreconditionName.EMPTY;
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

	public String toString() {
		return "Empty("+o.id+")";
	}
}
