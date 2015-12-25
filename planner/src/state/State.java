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
	
	/**
	 * @return the preconditions
	 */
	public Map<String, List<Predicate>> getPreconditions() {
		return preconditions;
	}

	/**
	 * @param preconditions the preconditions to set
	 */
	public void setPreconditions(Map<String, List<Predicate>> preconditions) {
		this.preconditions = preconditions;
	}

	/**
	 * @return the operator
	 */
	public Operator getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	/**
	 * @return the nextState
	 */
	public State getNextState() {
		return nextState;
	}

	/**
	 * @param nextState the nextState to set
	 */
	public void setNextState(State nextState) {
		this.nextState = nextState;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nextState == null) ? 0 : nextState.hashCode());
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((preconditions == null) ? 0 : preconditions.hashCode());
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
		State other = (State) obj;
		
		if (preconditions == null) {
			if (other.preconditions != null)
				return false;
		} else if (!preconditions.equals(other.preconditions))
			return false;
		
		return true;
	}
	

	
	
}
