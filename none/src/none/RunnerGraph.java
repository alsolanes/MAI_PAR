package none;

import none.config.Loader;
import none.predicate._Predicate;
import none.state.Node;
import none.state.State;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by j on 12/12/2015.
 */
public class RunnerGraph extends Runner{

    public RunnerGraph() {
        super();
    }

    @Override
    public void execute(Loader config){
        this.metrics = new Metrics();
        this.metrics.keepTrack("\nstart");
        System.out.println("Runner Restriction Runner");


        Node initState = new State(config.getInitialState(), config);
        Node goalState = new State(config.getGoalState(), config);

        // Deque<Node> expansionTree = new LinkedList<>(); // cola de exploracion
        // expansionTree.add(initState);
        this.plan = new LinkedList<>(); // linked list of nodes
        Deque<_Predicate> s = initState.getDiff(goalState);

        while (s.size() > 0 && !this.planFound){

            _Predicate p = s.removeFirst(); // retrieve a condition from the diff deque

            this.modifyPlan(p); // append plan exention
            // modify-plan(plan, p)
            this.expand(s); // extend plan


        } // fi mientras


        // com implementar la versió xarxa de tot lo que hagi de fer?
        //




        if(this.planFound){
            System.out.println(this.plan);
        }else{ // plan impossible to find
            System.out.println("They is no way to find the plan");
        }
        System.out.println("\nElapsed time: " + this.metrics.getElapsed());
    }

    private void modifyPlan(_Predicate p){
        this.plan = null; // do whatever to the plan with predicate

    }

    private void expand(Deque<_Predicate> s){
        // do whatever to the predicate list
        // order deque by operation priority
    }
    public static void main(String[] args){
        RunnerGraph run = new RunnerGraph();
        Loader loader = new Loader(0);
        run.execute(loader);
    }
}
