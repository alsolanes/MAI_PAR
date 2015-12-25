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
		return "Adjacent("+o1.id+","+o2.id+")";
	}
}
