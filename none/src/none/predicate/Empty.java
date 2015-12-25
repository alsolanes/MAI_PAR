package none.predicate;

import none.building.Office;

/**
 * Created by j on 12/12/2015.
 */
public class Empty extends _Predicate{
    @Override
    public String toString() {
        return "Empty("+office.name+")";
    }

    public Empty(Office o) {
        this.office = o;
    }
}
