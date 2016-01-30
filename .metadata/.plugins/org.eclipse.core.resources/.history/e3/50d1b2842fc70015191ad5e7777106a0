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
		Empty other = (Empty) obj;
		if (o == null) {
			if (other.o != null)
				return false;
		} else if (!o.equals(other.o))
			return false;
		return true;
	}
	
}
