/**
 * 
 */
package predicate;

import item.Box;
import item.Office;

/**
 * @author Aleix
 *
 */
public abstract class Predicate {

	protected String name = "";
	
	public Predicate(){
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}