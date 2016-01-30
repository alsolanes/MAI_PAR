package lineal.stack.pre;

import lineal.stack.O;
import lineal.stack.P;

import java.util.List;

/**
 * Created by j on 16/01/2016.
 */
public class Adjacent extends P {

    String o1;
    String o2;
    // this class is static
    public Adjacent() {
        // empty constructor to leave the variables undefined

        this.o1 = null;

        this.o2 = null;
    }

    public Adjacent(String o1, String o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    @Override
    public boolean isParcialDefined(){
        return this.o1 == null || this.o2 == null;
    }

    public String getO1() {
        return o1;
    }

    public void setO1(String o1) {
        this.o1 = o1;
    }

    @Override
    public String toString() {

        // return super.toString();
        return "Adjacent(" + this.o1 + "," + this.o2 + ")";
    }
}
