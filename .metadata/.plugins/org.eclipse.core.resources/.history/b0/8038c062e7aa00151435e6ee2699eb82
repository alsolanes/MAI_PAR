package state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import operator.Operator;
import predicate.Predicate;

public class State {

	private Map<String, List<Predicate>> preconditions;
	private Operator operator;
	private State nextState;
	
	public State() {
		this.preconditions = new HashMap<String, List<Predicate>>();
	}
	
	public State(Map<String, List<Predicate>> preconditions) {
		this.preconditions = preconditions;
	}
	
}
