package predicate;

import constants.PreconditionName;
import item.Office;

/**
 * The robot is in office o
 * @author Aleix
 *
 */
public class RobotLocation extends Predicate {

	Office o;
	
	public RobotLocation(Office o){
		super();
		this.o = o;
		this.name = PreconditionName.ROBOTLOCATION;
	}
	
	public String toString(){
		return "Robot-location("+o.id+")";
	}
}
