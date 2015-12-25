package item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Office {
	public String id;
	public int position; //listed as 1,2,3;4,5,6;7,8,9 matrix style
	public ArrayList<Integer> adjacent_list;
	
	public Office(String id, int position){
		this.id = id;
		this.position = position;
		adjacent_list = new ArrayList<Integer>();
		define_adjacent();
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
}
