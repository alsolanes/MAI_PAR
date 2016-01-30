package lineal.stack;

import lineal.stack.pre.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by j on 16/01/2016.
 */
public class State implements E {
    // State is a list of predicates
    List<P> pre;

    public State() {
        this.pre = new LinkedList<>();
        // somehow this has to be sorted... or maybe not.
    }

    public State(List<P> list) {
        this.pre = list;

    }

    public void applyOp(O op){

        // do reflection
        op.apply(this); // this state apply ops
        // addPre(op.toString());

    }

    public void addPre(String p) {

        String methodName = p.substring(0, p.indexOf('('));
        switch (methodName) { // split the string and have the first halve
            case "Dirty":
                Dirty predD = new Dirty();
                predD.setAttr(p);
                pre.add(predD);
                break;
            case "Empty":
                Empty predE = new Empty();
                predE.setAttr(p);
                pre.add(predE);
                break;
            case "Clean":
                Clean predC = new Clean();
                predC.setAttr(p);
                pre.add(predC);
                break;
            case "Box-location":
                BoxLocation predBL = new BoxLocation();
                predBL.setAttr(p);
                pre.add(predBL);
                break;
            case "Robot-location":
                RobotLocation predRL = new RobotLocation();
                predRL.setAttr(p);
                pre.add(predRL);
                break;
            default:
                // unhandled error
                System.out.println("Unhandled predicate: "+methodName);
                break;
        }

    }


    public List<P> getPre() {
        return pre;
    }


    public boolean hasPre(P p){
        return this.pre.contains(p); // if it contains a certain string.
    }

    /**
     * Returns a list of none matching
     * @return
     */
    public List<E> hasAllPre(PList pl){
        List<E> temp = new LinkedList<>();
        for(Object p : pl.getList())
        {
            // return the p that are not satisfied
            if(this.pre.contains(p)){
                continue;
            }else{
                 temp.add((P) p);
            }
        }
        return temp;
    }

    public boolean hasCond(E cond){

        // buscar en el estado actual una instancia que satisfaga la condicion cond
        boolean hasCond = false;
        for(Object p : this.pre)
        {
            // return the p that are not satisfied
            // if(this.pre.contains(cond)){
            System.out.println("Has condition? "+p+" as "+cond +" ?");
            if(p.equals(cond)){
                hasCond = true;
                // transmitir a la pila????? WTF
                // todo No se esto, supongo que quiere que actualize la variable en PList
                //
                System.out.println("Has condition! "+p+"");
            }else{
                continue;
            }
        }
        return hasCond;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
