package predicate;

import java.util.ArrayList;

public class PredicateList {
	private ArrayList<Predicate> predicates_list;
	
	public PredicateList(){
		predicates_list = new ArrayList<Predicate>();
	}
	public PredicateList(ArrayList<Predicate> list_pred){
		predicates_list = list_pred;
	}
}
