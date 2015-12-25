package none.predicate;

import none.building.Box;
import none.building.Office;

/**
 * Created by j on 12/12/2015.
 */
public abstract class _Predicate implements Comparable{
    Box box;
    Office office;

    @Override
    public String toString() {
        return super.toString();

    }

    public Office getOffice(){
        return office;
    }

    public Box getBox(){
        return box;
    }



    // per tenir un hash map consistent s'ha de tenir els _Predicats ordenats
    @Override
    public int compareTo(Object o) {
        return this.toString().compareTo(o.toString());
    }
}
