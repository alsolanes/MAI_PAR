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
		// TODO Auto-generated method stub
		System.out.println("Planning and approximate reasoning");
		System.out.println("\tAuthors: \tPablo Martinez & Aleix Solanes");
		System.out.println("\tAcademic Year: \t2015/16");
		ConfigLoader loader = new ConfigLoader();
		//loader.load("config_example.txt");
		loader.load("config_example.txt");
		
		State tmp = new State(loader.getInitialState(), (ArrayList<Office>)loader.getOffices());
		
		loader.load("config_example2.txt");
		
		State tmp2 = new State(loader.getInitialState(), (ArrayList<Office>)loader.getOffices());
		
		RegressionPlanner r = new RegressionPlanner(loader.getState_Initial(), loader.getState_Goal());
		System.out.println(r.getValueState(tmp));
		//Operator op = new Move(loader.getOffice("o4"), loader.getOffice("o5"));
		//Operator op = new Push(loader.getBox("A"),loader.getOffice("o5"),loader.getOffice("o2"));
		/*Operator op = new CleanOffice(loader.getOffice("o1"));
		if(tmp.isValidOperator(op, tmp))
			System.out.println("Valid");
		else
			System.out.println("Not valid");*/
		
		System.out.println(tmp.possibleActions().toString());
		
		/*Predicate cln = new Clean(loader.getOffice("o2"));
		Predicate bl = new BoxLocation(loader.getBox("B"),loader.getOffice("o6"));
		ArrayList<Predicate> lst = new ArrayList<Predicate>();
		lst.add(cln); lst.add(bl);
		
		tmp.deletePredicates(lst);
		
		System.out.println("New state: \n"+tmp.toString());
		
		tmp.addPredicates(lst);
		
		System.out.println("New state: \n"+tmp.toString());*/
		
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
