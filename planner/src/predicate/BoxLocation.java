package predicate;

import constants.PreconditionName;
import item.Box;
import item.Office;

/**
 * Box b is located in office o.
 * @author Aleix
 *
 */
public class BoxLocation extends Predicate {
	
	Office o;
	Box b;
	
	public BoxLocation(Box b, Office o){
		super();
		this.o = o;
		this.b = b;
		this.name = PreconditionName.BOXLOCATION;
	}
	
	public String toString(){
		return "Box-location("+ b.id + "," + o.id + ")";
	}
}
