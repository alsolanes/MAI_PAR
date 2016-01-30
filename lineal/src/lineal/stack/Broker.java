package lineal.stack;

import lineal.stack.ops.CleanOffice;
import lineal.stack.ops.Move;
import lineal.stack.ops.Push;
import lineal.stack.pre.BoxLocation;
import lineal.stack.pre.Clean;
import lineal.stack.pre.Empty;
import lineal.stack.pre.RobotLocation;

import java.util.*;

/**
 * Created by j on 26/01/2016.
 */
public class Broker {

    // keep track of addlist candidates and adjacency candidates
    List<String> boxes;
    List<String> offices;
    int dimension;
    Map<String, TreeSet<String>> adjacent;
    Map<String, TreeSet<String>> condOp; // operatdores que al aplicarse satisface una condicion
    State currentState;
    public Broker(HashMap<String, List> map, HashMap adjacent, State currState){
        // System.out.println(map);
        this.currentState = currState;
        this.adjacent = adjacent;
        this.boxes = map.get("Boxes");
        this.offices = map.get("Offices");
        System.out.println(this.boxes);
        System.out.println(this.offices);
        System.out.println(this.adjacent);
        this.dimension = (int)Math.sqrt(this.offices.size());

        condOp = new HashMap<>();

        /*

         */

    }

    private String assignAdjacent(String src, String tgt){
        String result = null;
        if(this.dimension != 2){

            // take one that is adjacent to current or adjacent to target which is adjacent to current
            // if src is adjacent to tgt then pick src
            // otherwise choose one that is adjacent to source and target
            // this could be a regressive loop
            TreeSet<String> adjacentsTgt = this.adjacent.get(tgt); // target adjacent
            if(adjacentsTgt.contains(src))
                return src;


            TreeSet<String> adjacentsSrc = this.adjacent.get(src); // source adjacent

            TreeSet<String> mixing = new TreeSet<>(adjacentsSrc);

            mixing.retainAll(adjacentsTgt);
            if(mixing.size() == 0){
                return adjacentsTgt.first();
                // return adjacentsTgt.get(0);
            }

            return mixing.first();
            // case 1 at one step
            // case 2 at adjacentsSrc and adjacentsTgt // have mixing
            // otherwise choose random tgt neighbor
        }
        // take one
        return this.adjacent.get(tgt).first();
        // return null; // this will never happen!!!

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
                // Move(null,Y)
                String o1 = assignAdjacent(this.currentState.getRobotLocation().getO(), move.getO2());
                move.setO1(o1);
                // Move(X,Y)
                // assign its ajacent here
                // but what o1 should i choose?, choose the one that is adjacent to current position, if the dim is 3 otherwise just take one
                // incognita fixed
                return move;
                // move
                // break;
            case "Empty":
                // push // this should not be done? as this goal will be achived when box location is correct
                // push whatever box
                // Push(B,X,Y)
                Push pushEmpty =  new Push(); // push the box on the office to somewhere else
                pushEmpty.setO1(((Empty)p).getO());
                // know what box is on the pushEmpty
                String box = this.currentState.getBoxLocation(pushEmpty.getO1());
                pushEmpty.setB(box);
                String o2 = this.currentState.getFreeAdjacent(this.adjacent.get(pushEmpty.getO1()));
                if(o2 == null){
                    System.out.println("Block got Stucked");
                    // move this operator to the tail?
                    // or just noop and force the need to move a neighbor block
                }else{
                    pushEmpty.setO2(o2);
                }
                return pushEmpty;
                // break;
            case "BoxLocation": // push a certain box to that office
                // push a certain box
                Push pushBox =  new Push();
                pushBox.setB(((BoxLocation)p).getB());
                pushBox.setO2(((BoxLocation)p).getO());
                // here the move rules applies
                String pushO1 = assignAdjacent(this.currentState.getRobotLocation().getO(), pushBox.getO2());
                pushBox.setO1(pushO1);
                return pushBox;
                // push
            case "Clean": // clean that office
                // clean a certain office
                // have to move to that office???
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
