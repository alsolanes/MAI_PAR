package none.building;

import java.util.*;

/**
 * Created by x on 3/12/15.
 */
public class Office{

    Map<String, Box> box_list;
    Map<String, Office> adjacent_list;  // static
    public String name; // static

    public Office(String name){
        this.name = name;
        this.adjacent_list = new HashMap<String, Office>();
    }

    public void putAdjacent(Office o){
        adjacent_list.put(o.name, o);
    }
    public Set<String> listAdjacents(){
        return adjacent_list.keySet();
    }
    public Collection<Office> getAdjacents(){
        return adjacent_list.values();
    }
    public boolean isAdjacent(Office o){
        return adjacent_list.containsValue(o);
    }

}
