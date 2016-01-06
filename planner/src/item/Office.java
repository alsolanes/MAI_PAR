package item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Office {
	public String id;
	public int position; //listed as 1,2,3;4,5,6;7,8,9 matrix style
	public int row, column;
	public ArrayList<Integer> adjacent_list;
	
	public Office(String id, int position){
		this.id = id;
		this.position = position;
		adjacent_list = new ArrayList<Integer>();
		define_adjacent();
		id2RowCol();
	}
	
	public Boolean is_adjacent(Office off){
		return this.adjacent_list.contains(off.position);
	}
	
	/**
	 * Based on matrix with positions 1,2,3;4,5,6...
	 */
	public void define_adjacent(){
		if (position <=6)
			adjacent_list.add(position+3);
		if (position >= 4)
			adjacent_list.add(position-3);
		if (position%3 != 0)
			adjacent_list.add(position+1);
		if (position != 1 && position != 4 && position != 7)
			adjacent_list.add(position-1);
	}
	
	public void id2RowCol(){
		if(position<4) {
			row = 0; column = position-1;
		}
		else if(position<7){
			row = 1; column = position-4;
		}
		else{
			row = 2; column = position-7;
		}
	}
	
	public String toString(){
		return "Office: "+id+"\nPos: "+position+"\nRow: "+row+"\tCol: "+column;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adjacent_list == null) ? 0 : adjacent_list.hashCode());
		result = prime * result + column;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + position;
		result = prime * result + row;
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
		Office other = (Office) obj;
		if (adjacent_list == null) {
			if (other.adjacent_list != null)
				return false;
		} else if (!adjacent_list.equals(other.adjacent_list))
			return false;
		if (column != other.column)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (position != other.position)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	
}
