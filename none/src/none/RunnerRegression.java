package none;

import none.config.Loader;
import none.operator.Move;
import none.operator._Operator;
import none.predicate._Predicate;
import none.state.State;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by j on 12/12/2015.
 */
public class RunnerRegression extends Runner {


    static int level = 4;
    int stateAppend = 0;
    int stateSkipped = 0;
    int stateRevert = 0;
    int depth = 0;
    int depthRevert = 0; // rever index
    int depthLimit = 100;
    int closest = Integer.MAX_VALUE;
    static String output_path = "src/none/config/solution/";
    static String output = output_path + "output_" + level + ".log";

    public RunnerRegression() {
        super();
        this.planFound = false;
    }

    public static void main(String[] args) throws IOException {
        RunnerRegression run = new RunnerRegression();
        BufferedWriter logger = new BufferedWriter(new FileWriter(new File(output)));
        Loader loader = new Loader(level);
        run.execute(loader);
        logger.write(run.metrics.toString());
        logger.flush();
        logger.close();
    }

    @Override
    public void execute(Loader config) {
        this.metrics = new Metrics();
        this.metrics.keepTrack("\nstart");

        State initState = new State(config.getInitialState(), config);
        State goalState = new State(config.getGoalState(), config);

        System.out.println("InitialState: \t");
        System.out.println(config.getInitialState());
        System.out.println("GoalState: \t");
        System.out.println(config.getGoalState());

        HashSet<State> expansionHistory = new HashSet<>(); // contains a history of all the expanded states / hash, state
        Deque<State> expansionDeque = new LinkedList<>(); // cola de exploracion
        Deque<State> nextExpansionDeque = new LinkedList<>();
        Map<Integer, Map<String, State>> residualMapDeque = new HashMap<>();
        Map<String, State> residualDeque = new HashMap<>();
        residualMapDeque.put(depth, residualDeque);
        // nivel
        expansionDeque.add(goalState);
        expansionHistory.add(goalState);

        // int depth = 0;

        while (expansionDeque.size() > 0 && !(this.planState instanceof State) && depth < depthLimit) {
            State s = expansionDeque.remove();
            this.planState = s.expand(initState, expansionHistory, nextExpansionDeque); // current always push to next
            if (expansionDeque.size() == 0) { // new depth
                this.depth++;
                // System.out.println("\n\nNew depth: "+this.depth+" >> "+nextExpansionDeque.size()+" \n ");
                expansionDeque = nextExpansionDeque; //
                nextExpansionDeque = new LinkedList<>();
            }
        } // fi mientras

        if(this.planState instanceof State){
            this.plan = this.planState.getPlan();
            this.planFound = true;
        }else{
            this.planFound = false;
        }
        this.metrics.keepTrack("\nExit Loop... " + this.planFound);
        if (this.planFound) {
            System.out.println("\nSOLUTION: \n");
            //System.out.println(this.plan);
            int steps = 1;
            for (_Operator op : this.plan) {
                System.out.println(steps++ + "\t --> " + op.toString());
            }
            System.out.println("");
        } else { // plan impossible to find
            State s = new State();
            String ss = s.bestMatch +"   \t   "+s.skipMatch + "   \t   "+s.loopMatch + "   \t   "+s.stateCounter;
            System.out.println("bm   \t   sm   \t   lm   \t   sc");
            System.out.println(ss);
        }

        System.out.println("Runner Regression Runner");
        System.out.println("Elapsed time: " + this.metrics.getElapsed());
        System.out.println("Skipped:      " + this.stateSkipped);
        System.out.println("Appended:     " + this.stateAppend);
        System.out.println("Revert:       " + this.stateRevert);
        System.out.println("Depth:        " + this.depth);
        System.out.println("Closest:      " + this.closest);

        this.metrics.keepTrack("\nElapsed time: " + this.metrics.getElapsed());
        this.metrics.keepTrack("\nSkipped:      " + this.stateSkipped);
        this.metrics.keepTrack("\nAppended:     " + this.stateAppend);
        this.metrics.keepTrack("\nRevert:       " + this.stateRevert);
        this.metrics.keepTrack("\nDepth:        " + this.depth);
        this.metrics.keepTrack("\nClosest:      " + this.closest);



    }


}
