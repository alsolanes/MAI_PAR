package none.operator;

import none.building.Office;
import none.predicate.*;
import none.state.Node;
import none.state.State;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by j on 12/12/2015.
 */
public class CleanOffice extends _Operator {

    Office office;
    State parent;

    public CleanOffice(Office o, State parent) {
        super(parent.getPredicates());
        this.office = o; // target office
        this.parent = parent;
        // this.predicates = this.parent.getState();

    }



    public boolean reverse_check(){
        if(isUndo())
            return false;

        if (precondition.containsKey("Robot-Location(" + this.office.name + ")") &&
                precondition.containsKey("Clean(" + this.office.name + ")") &&
                precondition.containsKey("Empty(" + this.office.name + ")")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isUndo() {
        return false;
    }

    public void reverse_add(){
        _Predicate p = new Dirty(this.office);
        this.precondition.put(p.toString(), p);
    }

    public void reverse_remove(){
        this.precondition.remove("Clean("+this.office.name+")");
    }


    public boolean check() {
        if (precondition.containsKey("Robot-Location(" + this.office.name + ")") &&
                precondition.containsKey("Dirty(" + this.office.name + ")") &&
                precondition.containsKey("Empty(" + this.office.name + ")")) {
            return true;
        }
        return false;
    }

    public void add() {
        _Predicate p = new Clean(this.office);
        this.precondition.put(p.toString(), p);
        // remove them from the hash list

    }

    public void remove() {
        this.precondition.remove("Dirty(" + this.office.name + ")");
        // add them to the has list
    }

    public State apply() {
        State result = null;

        if (!this.check()) {
            // not possible :D
        } else {
            this.add();
            this.remove();
            result = (new State(
                    this.office,
                    new TreeSet<_Predicate>(this.precondition.values()),
                    this.parent,
                    this));
        }
        // System.out.println("CleanOffice instance");
        return result;
    }

    @Override
    public State reverse() {
        State result = null;
        if(reverse_check()){
            this.reverse_add();
            this.reverse_remove();
            result = new State(this.office,
                    new TreeSet<>(this.precondition.values()),
                    this.parent,
                    this);
        }
        // System.out.println("DirtyOffice instance");
        return result;
    }

    /*
    public State reverse(){
        // reverse stat path
        State result = null;
        if(reverse_check()){
            this.reverse_add();
            this.reverse_remove();
            result = new State(this.office, new TreeSet<>(this.precondition.values()),
            this.parent,
            this);
        }
        // System.out.println("DirtyOffice instance");
        return result;
    }
    */
    @Override
    public String toString() {
        return "CleanOffice("+this.office.name+")";
    }
}
