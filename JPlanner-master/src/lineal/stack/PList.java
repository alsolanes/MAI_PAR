package lineal.stack;

import java.util.*;

/**
 * Created by j on 23/01/2016.
 */
public class PList implements E {
    // continue una lista de condiciones
    List<P> list;

    public PList() {
        // empty constructor
        list = new LinkedList<>();
    }

    public void addCond(P p) {
        list.add(p);
    }

    public boolean rmCond(P p) {
        return list.remove(p);
    }

    public List getList(){
        return this.list;
    }
}
