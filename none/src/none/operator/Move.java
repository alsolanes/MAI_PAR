package none.operator;

import none.building.Office;
import none.predicate.*;
import none.state.State;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by j on 12/12/2015.
 */
public class Move extends _Operator {
    Office office;
    Office nextOffice;
    public State parent;

    public Move(Office o1, Office o2, State parent) {
        super(parent.getPredicates());
        this.office = o1;
        this.nextOffice = o2;
        this.parent = parent;

    }


    public boolean check() {
        if (this.office.isAdjacent(this.nextOffice) &&
                precondition.containsKey("Robot-Location(" + this.office.name + ")")) {
            return true;
        }
        return false;
    }

    public boolean reverse_check() {
        if (isUndo())
            return false;

        if (this.nextOffice.isAdjacent(this.office) &&
                precondition.containsKey("Robot-Location(" + this.nextOffice.name + ")")) {
            return true;
        }
        return false;
    }


    public void add() {
        //this.precondition.add(RobotLocation);
        _Predicate p = new RobotLocation(this.nextOffice);
        this.precondition.put(p.toString(), p);
    }

    public void remove() {
        //this.precondition.remove(RobotLocation);
        this.precondition.remove("Robot-Location(" + this.office.name + ")");
    }

    public void reverse_add() {
        //this.precondition.add(RobotLocation);
        _Predicate p = new RobotLocation(this.office);
        this.precondition.put(p.toString(), p);
    }

    public void reverse_remove() {
        //this.precondition.remove(RobotLocation);
        this.precondition.remove("Robot-Location(" + this.nextOffice.name + ")");
    }


    public State reverse() {
        State result = null;



        if (!this.reverse_check()) {

            // none possible
        } else {
            this.reverse_add();
            this.reverse_remove();
            result = new State(
                    this.office,
                    new TreeSet<_Predicate>(this.precondition.values()),
                    this.parent,
                    this);
        }
        return result;
    }

    public boolean isUndo() {
        // its undo only if the previous
        _Operator op = this.parent.getOperator();
        if (op instanceof Move) {
            if ((((Move) op).nextOffice) == this.office) {
                if (((Move) op).office == this.nextOffice) {
                    return true;
                }
            }
        }
        return false;
    }



    public State apply() {

        State result = null;


        if (!this.check()) {
            // none possible
        } else {
            this.add();
            this.remove();
            result = new State(
                    this.nextOffice,
                    new TreeSet<_Predicate>(this.precondition.values()),
                    this.parent,
                    this);
        }


        // System.out.println("Move instance ");
        return result;
    }

    @Override
    public String toString() {
        return "Move(" + this.office.name + "," + this.nextOffice.name + ")";
        //    return "Move("+this.nextOffice.name+","+this.office.name+")";
    }


}
