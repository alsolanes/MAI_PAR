package predicate;

import constants.PreconditionName;
import item.Office;

/**
 * Office o is dirty
 * @author Aleix
 *
 */
public class Dirty extends Predicate {

	private Office o;
	
	public Dirty(Office o){
		super();
		this.o = o;
		this.name = PreconditionName.DIRTY;
	}
	
	public String toString() {
		return "Dirty("+o.id+")";
	}
}
