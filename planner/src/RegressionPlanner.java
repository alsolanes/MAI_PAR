import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import constants.PreconditionName;
import item.Office;
import operator.Move;
import operator.Operator;
import operator.Push;
import predicate.Clean;
import predicate.Dirty;
import predicate.Predicate;
import state.Node;
import state.State;

/** 
 * This planner will begin from the goal and go backwards until achives the initial state.
 * This option, will consider goal stack planning and protective achieved goals to prevent errors.
 */

/**
 * @author Aleix
 *
 */
public class RegressionPlanner {
	State init_state, goal_state, curr_state;
	ArrayList<Operator> operator_solution;
	Operator curr_operator;
	ArrayList<State> state_solution;
	ArrayList<Node> nodes;
	 
	public RegressionPlanner(State init, State goal){
		init_state = init;
		goal_state = goal;
		operator_solution = new ArrayList<Operator>();
		state_solution = new ArrayList<State>();
		nodes = new ArrayList<Node>();
		addCleanResult();
	}
	
	/**
	 * Best action first
	 */
	public void execute_planner(){
		operator_solution = new ArrayList<Operator>();
		state_solution = new ArrayList<State>();
		nodes = new ArrayList<Node>();
		this.curr_state = this.init_state.copyState();
		while(!this.curr_state.equals(this.goal_state)){
			curr_operator = this.getBestOperator(this.curr_state);
			curr_state = generateNextState(curr_state, curr_operator);
			operator_solution.add(curr_operator);
			state_solution.add(curr_state);
		}
	}
	
	public void execute_planner_BFS(){
		operator_solution = new ArrayList<Operator>();
		state_solution = new ArrayList<State>();
		nodes = new ArrayList<Node>();
		this.curr_state = this.init_state.copyState();
		ArrayList<State> state_history = new ArrayList<State>();
		int depth = 0;
		Node n = new Node(this.curr_state,null,null,depth);
		nodes.add(n);
		boolean found = false;
		//repeat until a node has a solution
		while(!found){
			
			//create all children from current state
			for(Node m : get_levelNodes(depth)){
				for(Operator o: m.getCurrent_state().possibleActions()){
					State nextState = generateNextState(m.getCurrent_state(), o);
					if(!states_visited(m).contains(nextState)){
						//will add a node only if it is equal or closer to the goal state(by counting the different predicates)
						if(getDiffValState(m.getCurrent_state(), this.goal_state)>=getDiffValState(nextState,this.goal_state))
								nodes.add(new Node(nextState,m,o,depth+1));
					}
				}
				n=m;
				found = n.getCurrent_state().equals(this.goal_state);
				if(found){
					this.operator_solution=operators_visited(n);
					break;
				}
			}
			depth++;
		}
		
	}

	/**
	 * Returns a list of the nodes of the current depth level
	 * @param depth
	 * @return
	 */
	public ArrayList<Node> get_levelNodes(int depth){
		ArrayList<Node> out = new ArrayList<Node>();
		for(Node n : nodes){
			if(n.getDepth()==depth){
				out.add(n);
			}
		}
		return out;
	}
	/**
	 * Returns a list of the states from this node to the root
	 * @param n
	 * @return
	 */
	public ArrayList<State> states_visited(Node n){
		ArrayList<State> states_visit = new ArrayList<State>();
		states_visit.add(n.getCurrent_state());
		Node act_n = n;
		while(act_n.getDepth()>0){
			act_n = act_n.getParent_node();
			states_visit.add(act_n.getCurrent_state());
		}
		return states_visit;
	}
	
	/**
	 * Returns a list of the states from this node to the root
	 * @param n
	 * @return
	 */
	public ArrayList<Operator> operators_visited(Node n){
		ArrayList<Operator> operator_visit = new ArrayList<Operator>();
		operator_visit.add(n.getCurrent_operator());
		Node act_n = n;
		while(act_n.getDepth()>1){
			act_n = act_n.getParent_node();
			operator_visit.add(0,act_n.getCurrent_operator());
		}
		return operator_visit;
	}

	/**
	 * @return the init_state
	 */
	public State getInit_state() {
		return init_state;
	}


	/**
	 * @param init_state the init_state to set
	 */
	public void setInit_state(State init_state) {
		this.init_state = init_state;
	}


	/**
	 * @return the goal_state
	 */
	public State getGoal_state() {
		return goal_state;
	}


	/**
	 * @param goal_state the goal_state to set
	 */
	public void setGoal_state(State goal_state) {
		this.goal_state = goal_state;
	}


	/**
	 * @return the curr_state
	 */
	public State getCurr_state() {
		return curr_state;
	}


	/**
	 * @param curr_state the curr_state to set
	 */
	public void setCurr_state(State curr_state) {
		this.curr_state = curr_state;
	}


	/**
	 * @return the operator_solution
	 */
	public ArrayList<Operator> getOperator_solution() {
		return operator_solution;
	}


	/**
	 * @param operator_solution the operator_solution to set
	 */
	public void setOperator_solution(ArrayList<Operator> operator_solution) {
		this.operator_solution = operator_solution;
	}


	/**
	 * @return the curr_operator
	 */
	public Operator getCurr_operator() {
		return curr_operator;
	}


	/**
	 * @param curr_operator the curr_operator to set
	 */
	public void setCurr_operator(Operator curr_operator) {
		this.curr_operator = curr_operator;
	}


	/**
	 * @return the state_solution
	 */
	public ArrayList<State> getState_solution() {
		return state_solution;
	}


	/**
	 * @param state_solution the state_solution to set
	 */
	public void setState_solution(ArrayList<State> state_solution) {
		this.state_solution = state_solution;
	}


	/**
	 * Check the 
	 * @param a
	 * @param op
	 * @return
	 */
	public State generateNextState(State a, Operator op){
		if(op==null){
			System.out.println("Null operator");
			return null;
		}
		State out = a.copyState();
		out.deletePredicates(op.getList_delete());
		out.addPredicates(op.getList_add());
		return out;
	}
	/**
	 * Adds the predicates Clean to all offices, because by default are not included
	 */
	public void addCleanResult(){
		ArrayList<Predicate> out = new ArrayList<Predicate>();
		for(Office a : goal_state.getOffices()){
			Clean c = new Clean(a);
			out.add(c);
		}
		goal_state.addPredicates(out);
	}
	
	public int getValueState(Operator o,State a){
		int out = 0;
		//get the number of different items from this state and the goal
		int dif = getDiffValState(a, this.goal_state);
		//System.out.print(o.toString()+"\tDif val:"+dif);
		if(dif<2){
			if(o.getName().contains("MOVE"))
				out+=30;
		}
		if(o.getName().contains("PUSH")){
			Push p = (Push) o;
			Dirty d = new Dirty(p.getO2());
			//moving a box to a dirty office
			if(a.getPredicates().contains(d))
				out -= 30;
			//moving a box to its goal office
			else if(p.getO2().equals(this.goal_state.getBoxOffice(p.getB()))){
				out += 30;
			}
			//moving a box from its goal office
			if(p.getO1().equals(this.goal_state.getBoxOffice(p.getB()))){
				Dirty d2 = new Dirty(p.getO1());
				//if the place where the box is, it is dirty
				if(a.getPredicates().contains(d2)){
					out += 3;
				} else{
					out -= 3;
				}
			}
			else
				out = 1;
		} else if(o.getName().contains("CLEAN")){
			out += 35;
		} else if(o.getName().contains("MOVE")){
			Move m = (Move) o;
			Dirty d3 = new Dirty(m.getO2());
			// moving to a dirty office
			if(m.getO2().equals(d3)){
				out += 20;
			}
		}
		//Check if the robot is at a dirty place
		if(a.robotAtDirty()){
			out += 10;
			if(a.robotAtEmpty())
				out += 20;
			else
				out += 1;
		}
		
		out += 100-dif;
		//System.out.print("\tOp val:"+out+"\n");
		return out;
		
	}
	// Returns the number of similar predicates between two states
	public int getDiffValState(State a, State b){
		int out = 0;
		ArrayList<Predicate> p_list = (ArrayList<Predicate>) a.getPredicates();
		for(Predicate a_p : p_list){
			if(!b.getPredicates().contains(a_p))
				out++;
		}
		//System.out.println(sim);
		return out;
	}
	
	public Operator getBestOperator(State a){
		Operator out = null;
		int out_2 = 0;
		if(a.possibleActions()==null){
			System.out.println("No actions allowed");
			return null;
		}
		for (Operator o : a.possibleActions()){
			State tmp = a.copyState();
			//System.out.println("Checking:"+o.toString());
			tmp = this.generateNextState(tmp, o);
			if(state_solution!=null)
				if(state_solution.contains(tmp)) 
					continue; // visited states keep discarted
			int tmp_2 = getValueState(o,tmp);
			if(tmp_2>out_2){
				out = o;
				out_2 = tmp_2;
			}
		}

		return out;
	}
}
