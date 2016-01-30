import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import item.Box;

import config.ConfigLoader;
import item.Office;
import operator.CleanOffice;
import operator.Move;
import operator.Operator;
import operator.Push;
import predicate.BoxLocation;
import predicate.Clean;
import predicate.Predicate;
import state.State;

public class MyMain {

	public static void main(String[] args) {
		System.out.println("Planning and approximate reasoning");
		System.out.println("\tAuthors: \tPablo Martinez & Aleix Solanes");
		System.out.println("\tAcademic Year: \t2015/16");
		ConfigLoader loader = new ConfigLoader();
		ArrayList<String> files = new ArrayList<String>();
		files.add("config_2Box.txt");
		//files.add("config_2Box_1.txt");
		//files.add("config_3Box_1.txt");
		//files.add("config_4Box.txt");
		for(String file_name : files){
			System.out.println("File: "+file_name);
			
			loader.load(file_name);
			
			State tmp = new State(loader.getInitialState(), (ArrayList<Office>)loader.getOffices());
			System.out.println(tmp.printState());
			Planner r = new Planner(loader.getState_Initial(), loader.getState_Goal());

			
			/*r.execute_planner();
			System.out.println("Best first:"+r.getOperator_solution().toString());
			r.execute_planner_BFS();
			System.out.println("Optimal:"+r.getOperator_solution().toString());*/
			
			System.out.println("Goal Stack planner");
			r.execute_goalstack_planner();
		}
		/*loader.load("config_3Box.txt");
		
		State tmp = new State(loader.getInitialState(), (ArrayList<Office>)loader.getOffices());
		
		RegressionPlanner r = new RegressionPlanner(loader.getState_Initial(), loader.getState_Goal());

		
		r.execute_planner();
		System.out.println("Best first:"+r.getOperator_solution().toString());*/

		
	}
	
	public void printInitAndGoal(ConfigLoader loader){
		System.out.println("Boxes: ");
		List<Box> bxs = loader.getBoxes();
		for(Box b : bxs){
			System.out.print(b.toString());
		}
		System.out.println();
		System.out.println();
		System.out.println("\nPrinting:\n");
		List<Predicate> initState = loader.getInitialState();
		System.out.println("\nInitial State:");
		for (Predicate p : initState){
			System.out.print(p.toString()+ " ");
		}
		System.out.println("\nGoal State:");
		List<Predicate> goalState = loader.getGoalState();
		for (Predicate p : goalState){
			System.out.print(p.toString()+" ");
		}
	}
}
