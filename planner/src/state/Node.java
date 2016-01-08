package state;

import java.util.ArrayList;

import operator.Operator;

public class Node {
	private State current_state;
	private Operator current_operator;
	private Node parent_node;
	private int depth;
	
	public Node(){
	}
	
	public Node(State curr_state, Node parent, Operator curr_operator, int depth){
		this.current_operator = curr_operator;
		this.current_state = curr_state;
		this.parent_node = parent;
		this.depth = depth;
	}

	/**
	 * @return the current_state
	 */
	public State getCurrent_state() {
		return current_state;
	}

	/**
	 * @param current_state the current_state to set
	 */
	public void setCurrent_state(State current_state) {
		this.current_state = current_state;
	}


	/**
	 * @return the current_operator
	 */
	public Operator getCurrent_operator() {
		return current_operator;
	}

	/**
	 * @param current_operator the current_operator to set
	 */
	public void setCurrent_operator(Operator current_operator) {
		this.current_operator = current_operator;
	}

	/**
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * @param depth the depth to set
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Node getParent_node() {
		return parent_node;
	}

	public void setParent_node(Node parent_node) {
		this.parent_node = parent_node;
	}
	
	public String toString(){
		if(depth==0) return "Root node state = "+this.current_state.toString();
		return "Node depth = "+this.depth+"\nOperator = "+this.current_operator.toString()+"\nState = "+this.current_state.toString();
	}
	
}
