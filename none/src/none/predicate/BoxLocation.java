package none.predicate;

import none.building.Box;
import none.building.Office;

/**
 * Created by j on 12/12/2015.
 */
public class BoxLocation extends _Predicate{



    @Override
    public String toString() {
        return "Box-Location("+box.name+","+office.name+")";
    }
    public BoxLocation(Box b, Office o) {
        this.box = b;
        this.office = o;
    }
}
