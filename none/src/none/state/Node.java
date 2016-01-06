package none.state;

import none.building.Office;
import none.operator._Operator;
import none.predicate._Predicate;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by j on 12/12/2015.
 */
public abstract class Node {
    Node parent;
    int depth; // only root has depth 0;
    int distance;
    Set<Node> child;
    Set<_Predicate> predicates;
    _Operator operator;

    /*
    public boolean isSame(Node n){
        return true;
    }
    */

    public Deque<_Operator> getPlan() {
        Deque<_Operator> plan = new LinkedList<>();
        for (Node node : getTrace()) {
            plan.add(node.operator);
        }
        // System.out.println("ObtainedPlanSize: "+plan.size());
        return plan;
    }

    public _Operator getOperator() {
        return this.operator;
    }

    public Deque<Node> getTrace() {
        Deque<Node> deque = new LinkedList<>();
        Node n = this;
        try {
            do {
                deque.add(n);
                n = n.parent;
            } while (n.parent != null);
        }catch (NullPointerException ex){
            // System.out.println(ex.getMessage());
        }
        // System.out.println("DequeSize: "+deque.size());
        return deque;
    }

    public Deque<_Predicate> getDiff(Node node) {
        Deque<_Predicate> diff = new LinkedList<>();
        return diff;
    }

    public Set<_Predicate> getPredicates() {
        return this.predicates;
    }

    public int getDistance(){
        return this.distance;
    }
    public int getDepth(){
        return this.depth;
    }

}
