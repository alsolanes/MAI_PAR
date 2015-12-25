package none;

import none.config.Loader;
import none.operator._Operator;
import none.state.Node;
import none.state.State;

import java.util.Deque;

/**
 * Created by j on 12/12/2015.
 */
public abstract class Runner {

    Metrics metrics;
    State planState;
    Boolean planFound;
    Deque<_Operator> plan; // ordered deck of queue // double ended list...

    public Runner() {
    }

    /**
     * Move here the common initiation part
     * @param config
     */
    public void execute(Loader config){
        this.metrics = new Metrics();
        this.metrics.keepTrack("start");



        this.metrics.getElapsed();
    }


}
