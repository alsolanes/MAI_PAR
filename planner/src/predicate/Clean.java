package predicate;

import constants.PreconditionName;
import item.Office;

/**
 * Office o is clean
 * @author Aleix
 *
 */
public class Clean extends Predicate {

	private Office o;
	
	public Clean(Office o){
		super();
		this.o = o;
		this.name = PreconditionName.CLEAN;
	}
	
	public String toString(){
		return "Clean("+o.id+")";
	}
}
