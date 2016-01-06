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

	@Override
	public Office getOffice() {
		return o;
	}
	public Box getBox(){
		return b;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((b == null) ? 0 : b.hashCode());
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
		BoxLocation other = (BoxLocation) obj;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		if (o == null) {
			if (other.o != null)
				return false;
		} else if (!o.equals(other.o))
			return false;
		return true;
	}
}
