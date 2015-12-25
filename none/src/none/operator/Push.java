package none.operator;

import none.building.Box;
import none.building.Office;
import none.predicate.*;
import none.state.State;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by j on 12/12/2015.
 */
public class Push extends _Operator {

    Box box;
    Office office;
    Office nextOffice;
    State parent;

    public Push(Box b, Office o1, Office o2, State parent) {
        super(parent.getPredicates());
        this.box = b;
        this.office = o1;
        this.nextOffice = o2;
        this.parent = parent;
    }

    public boolean check() {
        try {
            if (this.office.isAdjacent(this.nextOffice) &&
                    this.precondition.containsKey("Robot-Location(" + this.office.name + ")") &&
                    this.precondition.containsKey("Box-Location(" + this.box.name + "," + this.office.name + ")") &&
                    this.precondition.containsKey("Empty(" + this.nextOffice.name + ")")) {
                //&& this.office.listAdjacents().contains(this.nextOffice))
                return true;
            }
        }catch (NullPointerException e){
            System.out.print(e.getMessage());
        }
        return false;
    }

    public boolean isUndo(){

        // its undo only if the previous
        _Operator op = this.parent.getOperator();
        if( op instanceof Push){
            //
            if(((Push) op).box == this.box){
                if((((Push) op).nextOffice) == this.office){
                    if(((Push) op).office == this.nextOffice){
                        return true;
                    }
                }
            }

        }

        return false;
    }

    public boolean reverse_check(){

        if(this.isUndo())
           return false;


        try {
            if (this.office.isAdjacent(this.nextOffice) &&
                    this.precondition.containsKey("Robot-Location(" + this.nextOffice.name + ")") &&
                    this.precondition.containsKey("Box-Location(" + this.box.name + "," + this.nextOffice.name + ")") &&
                    this.precondition.containsKey("Empty(" + this.office.name + ")")) {
                //&& this.office.listAdjacents().contains(this.nextOffice))
                return true;
            }
        }catch (NullPointerException e){
            System.out.print(e.getMessage());
        }
        return false;
    }


    public void add() {
        _Predicate p1 = new BoxLocation(this.box, this.nextOffice);
        this.precondition.put(p1.toString(), p1);
        _Predicate p2 = new RobotLocation(this.nextOffice);
        this.precondition.put(p2.toString(), p2);
        _Predicate p3 = new Empty(this.office);
        this.precondition.put(p3.toString(), p3);
    }

    public void remove() {
        this.precondition.remove("Empty(" + this.nextOffice.name + ")");
        this.precondition.remove("Box-Location(" + this.box.name + "," + this.office.name + ")");
        this.precondition.remove("Robot-Location(" + this.office.name + ")");
    }


    public State reverse(){
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


        // System.out.println("Push instance ");
        return result;
        // i can push the box to many adjacent

    }

    public void reverse_add(){
        _Predicate p1 = new BoxLocation(this.box, this.office);
        this.precondition.put(p1.toString(), p1);
        _Predicate p2 = new RobotLocation(this.office);
        this.precondition.put(p2.toString(), p2);
        _Predicate p3 = new Empty(this.nextOffice);
        this.precondition.put(p3.toString(), p3);
    }

    public void reverse_remove(){
        this.precondition.remove("Empty(" + this.office.name + ")");
        this.precondition.remove("Box-Location(" + this.box.name + "," + this.nextOffice.name + ")");
        this.precondition.remove("Robot-Location(" + this.nextOffice.name + ")");
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


        // System.out.println("Push instance ");
        return result;
        // i can push the box to many adjacent
    }

    @Override
    public String toString() {
        return "Push("+this.box.name+","+this.office.name+","+this.nextOffice.name+")";
       // return "Push("+this.box.name+","+this.nextOffice.name+","+this.office.name+")";
    }
}
