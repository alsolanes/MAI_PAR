package config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import item.Box;
import item.Office;
import operator.CleanOffice;
import predicate.BoxLocation;
import predicate.Clean;
import predicate.Dirty;
import predicate.Empty;
import predicate.Predicate;
import predicate.RobotLocation;
import state.State;

/**
 * This class will load the configuration files.
 * The initial and goal states.
 * @author Aleix
 *
 */
public class ConfigLoader {
	
	String file_path;
	ArrayList<Box> boxes;
	ArrayList<Office> offices;
	ArrayList<Predicate> initialState_list, goalState_list;
	State initialState, goalState;
	Map<String, List<String>> config; 
	
	public ConfigLoader(){
		this.file_path = System.getProperty("user.dir") + "/src/config/";
	}
	
	public void load(String file){
		this.file_path = System.getProperty("user.dir") + "/src/config/"+file;
		System.out.println("loading...");
		config = this.loadConfFile();
		this.offices = (ArrayList<Office>) getOffices();
		this.boxes = (ArrayList<Box>) getBoxes();
		this.initialState_list = (ArrayList<Predicate>) getInitialState();
		this.goalState_list = (ArrayList<Predicate>) getGoalState();
		System.out.println("Correctly loaded.");
	}
	
	public State getState_Initial(){
		State out = new State(this.initialState_list,this.offices);
		return out;
	}
	
	public State getState_Goal(){
		State out = new State(this.goalState_list, this.offices);
		return out;
	}
	
	/**
	 * Reads a conf file, and returns a map. The key will be the string "Boxes", "Offices", "InitialState" or "GoalState"
	 * The List<String> will contain all predicates in String format related to each key.
	 * @return
	 */
	public Map<String, List<String>> loadConfFile(){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(this.file_path));
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Map<String, List<String>> properties = new HashMap();
		try {
			
			String line = br.readLine();
			
			String key = null,cur_vals = null;
			String[] vals = null;
			
			while(line!=null){
				//first we remove all whitespaces
				line = line.replaceAll("\\s+", "");
				//then we locate the "=" symbol.
				
				if(line.indexOf("=")!=-1){
					String[] items = line.split("=");
					key = items[0];
					cur_vals = items[1];
					properties.put(key, new ArrayList<String>());
				}
				if(line.indexOf(";")!=-1){
					vals = cur_vals.split(";");
				} else if(line.indexOf(",")!=-1){
					vals = cur_vals.split(",");
				}
				for(String val : vals)
					properties.get(key).add(val);
				
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return properties;
	}
	
	/**
	 * Returns the initial state in string list format.
	 * @return
	 */
	public List<Predicate> getInitialState(){
		List<String> initState = new ArrayList<String>();
		initState = config.get("InitialState");
		initialState_list =  (ArrayList<Predicate>) parsePredicates(initState);
		return initialState_list;
	}
	
	/**
	 * Returns the goal state in string list format.
	 * @return
	 */
	public List<Predicate> getGoalState(){
		List<String> goalState = new ArrayList<String>();
		goalState = config.get("GoalState");
		this.goalState_list = (ArrayList<Predicate>) parsePredicates(goalState);
		return this.goalState_list;
	}
	
	public List<Box> getBoxes(){
		List<String> boxes_string = new ArrayList<String>();
		boxes_string = config.get("Boxes");
		List<Box> boxes_list = new ArrayList<Box>();
		for(String b : boxes_string){
			boxes_list.add(new Box(b));
			
		}
		this.boxes = (ArrayList<Box>) boxes_list;
		return boxes_list;
	}
	
	public Box getBox(String id){
		for(Box b1:boxes){
			if(b1.id.equals(id)){
				return b1;
			}
		}
		return null;
	}
	
	/**
	 * Returns a list of offices taken from the file previously read.
	 * @return
	 */
	public List<Office> getOffices(){
		List<String> offic = new ArrayList<String>();
		List<Office> out = new ArrayList<Office>();
		offic = config.get("Offices");
		int i = 1;
		for (String off : offic){
			Office o = new Office(off,i);
			out.add(o);
			i++;
		}
		this.offices = (ArrayList<Office>) out;
		return out;
	}
	
	/**
	 * Returns an Office from an id
	 * @param id
	 * @return
	 */
	public Office getOffice(String id){
		if(offices.size() < 1)
			getOffices();
		for (Office o1: offices){
			if(o1.id.equals(id))
				return o1;
		}
		return null;
	}
	
	/**
	 * Prints a representation of the building in a 3by3 grid. 
	 * @return
	 */
	public String printStateMap(Map<String, List<String>> in){
		//Map<String, List<String>> in = parseInitialState();
		String out = "";
		int i = 0;
		for (String a:in.keySet()){
			i++;
			String out_aux = "";
			if(i==1 || i==4 || i==7) out+="\n---|---|---\n";
			else out+="|";
			for (String val:in.get(a)){
				
				if(val.contains("Robot")){
					out_aux+=val.substring(0, 1);
				}else if(val.contains("Box") || val.contains("Empty")){
					out_aux+=val.substring(0, 1);
				}else if(val.contains("Dirty") || val.contains("Clean")){
					out_aux+=val.substring(0, 1);
				}else{
					out_aux = "Error";
				}
			}
			if(out_aux.length()<3){
				int rem = 3-out_aux.length();
				for(int i1 = 0; i1<rem; i1++)
					out_aux+=" ";
			}
			out+=out_aux;
		}
		return out+="\n---|---|---\n";
	}
	
	/**
	 * prints a state
	 * @param in - a list with the predicates of the state
	 * @return
	 */
	/*
	public String printState(List<String> in){
		Map<Office, Predicate> mapOut = parseState(in);
		return printStateMap(mapOut);
	}
	*/

	
	
	public List<Predicate> parsePredicates(List<String> conditions_string){
		List<Predicate> out = new ArrayList<Predicate>();
		for(String pred : conditions_string){
			Predicate p = null;
			if(pred.contains("Box")){
				Box box = new Box(pred.substring(pred.indexOf("(")+1, pred.lastIndexOf(",")));
				Office office = getOffice(pred.substring(pred.indexOf(",")+1,pred.lastIndexOf(")")));
				p = new BoxLocation(box, office);
			}else if (pred.contains("Clean")){
				Office office = getOffice(pred.substring(pred.indexOf("(")+1, pred.lastIndexOf(")")));
				p = new Clean(office);
			}else if (pred.contains("Dirty")){
				Office office = getOffice(pred.substring(pred.indexOf("(")+1, pred.lastIndexOf(")")));
				p = new Dirty(office);
			}else if (pred.contains("Empty")){
				Office office = getOffice(pred.substring(pred.indexOf("(")+1, pred.lastIndexOf(")")));
				p = new Empty(office);
			}else if (pred.contains("Robot")){
				Office office = getOffice(pred.substring(pred.indexOf("(")+1, pred.lastIndexOf(")")));
				p = new RobotLocation(office);
			}
			if(p!=null)
				out.add(p);
		}
		return out;
	}
}
