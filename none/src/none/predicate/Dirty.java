package none.predicate;

import none.building.Office;

/**
 * Created by j on 12/12/2015.
 */
public class Dirty extends _Predicate{

    @Override
    public String toString() {

        return "Dirty("+office.name+")";
    }

    public Dirty(Office o) {
        this.office = o;
    }
}
