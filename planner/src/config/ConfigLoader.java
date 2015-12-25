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
	Map<String, List<String>> config; 
	
	public ConfigLoader(){
		this.file_path = System.getProperty("user.dir") + "/src/config/";
	}
	
	public void load(String file){
		this.file_path += file;
		System.out.println("loading...");
		config = this.loadConfFile();
		System.out.println(config);
	}
	
	public Map loadConfFile(){
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
	
	public List<String> getInitialState(){
		List<String> initState = new ArrayList<String>();
		initState = config.get("InitialState");
		return initState;
	}
	
	public List<String> getGoalState(){
		List<String> goalState = new ArrayList<String>();
		goalState = config.get("GoalState");
		return goalState;
	}
}
