package predicate;

import constants.PreconditionName;
import item.Office;

/**
 * The robot is in office o
 * @author Aleix
 *
 */
public class RobotLocation extends Predicate {

	private Office o;
	
	public RobotLocation(Office o){
		super();
		this.o = o;
		this.name = PreconditionName.ROBOTLOCATION;
	}
	
	public String toString(){
		return "Robot-location("+o.id+")";
	}

	public Office getOffice() {
		return o;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((o == null) ? 0 : o.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RobotLocation other = (RobotLocation) obj;
		if (o == null) {
			if (other.o != null)
				return false;
		} else if (!o.equals(other.o))
			return false;
		return true;
	}
	
}
