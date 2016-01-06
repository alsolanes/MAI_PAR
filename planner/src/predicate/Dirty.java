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

	@Override
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
		Dirty other = (Dirty) obj;
		if (o == null) {
			if (other.o != null)
				return false;
		} else if (!o.equals(other.o))
			return false;
		return true;
	}
	
}
