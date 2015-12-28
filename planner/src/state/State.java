package state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import constants.PreconditionName;
import item.Office;
import operator.CleanOffice;
import operator.Move;
import operator.Operator;
import operator.Push;
import predicate.Empty;
import predicate.Predicate;

public class State {

	private Map<String, List<Predicate>> conditions;
	private Operator operator;
	private State nextState;
	
	public State() {
		this.conditions = new HashMap<String, List<Predicate>>();
	}
	
	public State(Map<String, List<Predicate>> conditions) {
		this.conditions = conditions;
	}
	
	/**
	 * @return the conditions
	 */
	public Map<String, List<Predicate>> getConditions() {
		return conditions;
	}

	/**
	 * @param conditions 
	 * the conditions to set
	 */
	public void setConditions(Map<String, List<Predicate>> conditions) {
		this.conditions = conditions;
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
		result = prime * result + ((conditions == null) ? 0 : conditions.hashCode());
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
		
		if (conditions == null) {
			if (other.conditions != null)
				return false;
		} else if (!conditions.equals(other.conditions))
			return false;
		
		return true;
	}
	
	/**
	 * Check whether the state verifies the preconditions of the operator.
	 * @param op
	 * @param st
	 * @return
	 */
	public boolean isValidOperator(Operator op, State st){
		List<Predicate> lst_prec = (List<Predicate>) op.getList_preconditions().values();
		for(Predicate p : lst_prec){
			if(!st.getConditions().values().contains(p)){
				return false;
			}
		}
		return true;
	}

	
	
}
