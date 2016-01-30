package lineal.stack;

import lineal.stack.ops.CleanOffice;
import lineal.stack.ops.Move;
import lineal.stack.ops.Push;
import lineal.stack.pre.BoxLocation;
import lineal.stack.pre.Clean;
import lineal.stack.pre.Empty;
import lineal.stack.pre.RobotLocation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by j on 26/01/2016.
 */
public class Broker {

    // keep track of addlist candidates and adjacency candidates
    List<String> boxes;
    List<String> offices;
    int dimension;
    Map<String, List<String>> adjacent;
    Map<String, List<String>> condOp; // operatdores que al aplicarse satisface una condicion

    public Broker(HashMap<String, List> map, HashMap adjacent){
        // System.out.println(map);
        this.adjacent = adjacent;
        this.boxes = map.get("Boxes");
        this.offices = map.get("Offices");
        System.out.println(this.boxes);
        System.out.println(this.offices);
        System.out.println(this.adjacent);


        condOp = new HashMap<>();

        /*

         */

    }

    public O getOperator(P p){


        System.out.println(p);

        // given a p -> precondition
        // return a operator that might fulfill it.
        // at their add list

        // per prioritat

        switch (p.type){
            case "RobotLocation":
                Move move = new Move(); // our robot to the location
                move.setO2(((RobotLocation)p).getO());
                return move;
                // move
                // break;
            case "Empty":
                // push
                // push whatever box
                Push pushEmpty =  new Push(); // push the box on the office to somewhere else
                pushEmpty.setO1(((Empty)p).getO());
                return pushEmpty;
                // break;
            case "BoxLocation": // push a certain box to that office
                // push a certain box
                Push pushBox =  new Push();
                pushBox.setB(((BoxLocation)p).getB());
                pushBox.setO2(((BoxLocation)p).getO());
                return pushBox;
                // push
            case "Clean": // clean that office
                // cliean a certain office
                return  new CleanOffice(((Clean)p).getO());
                // cleanoffice
            case "Adjacent":
            case "Dirty":
            default:
                // noop
                break;
        }


        return null;
    }
}
