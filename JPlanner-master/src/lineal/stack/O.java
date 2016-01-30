package lineal.stack;
/**
 * Created by j on 16/01/2016.
 */
public abstract class O implements E {

    protected String type = "O";
    protected boolean defined = false;
    protected boolean precondition(){
        // lista de condiciones que deben de ser cumplidas para aplicar el operador

        return false;
    }

    protected boolean isDefined(){
        return this.defined;
    }


    protected void apply(State s){

    }

    protected void add(E e){

    }

    protected void remove(E e){

    }


    public PList getPredList(){
        // return the addlist predcondition
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
