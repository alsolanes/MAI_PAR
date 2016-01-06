import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import operator.Operator;
import predicate.Dirty;
import predicate.Predicate;
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
	Queue<State> state_path;
	State init_state, goal_state;
	
	public RegressionPlanner(State init, State goal){
		init_state = init;
		goal_state = goal;
	}
	
	public void execute_planner(){
		
	}
	
	/**
	 * Check the 
	 * @param a
	 * @param op
	 * @return
	 */
	public State generateNextState(State a, Operator op){
		
		
		return null;
	}
	
	public int getValueState(State a){
		int out = 0;
		//Check if the robot is at a dirty place
		Dirty d = new Dirty(a.getRobotPosition());
		if(a.robotAtDirty()){
			out += 1;
			if(a.robotAtEmpty())
				out += 2;
			else
				out += 1;
		}
		out += getDiffValState(a, this.goal_state);
		
		return out;
		
	}
	// Returns the number of similar predicates between two states
	public int getDiffValState(State a, State b){
		ArrayList<Predicate> sim = (ArrayList<Predicate>) a.getPredicates();
		sim.retainAll(b.getPredicates());
		//System.out.println(sim);
		return sim.size();
	}
}
