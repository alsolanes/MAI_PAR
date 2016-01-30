package lineal.stack.pre;

import lineal.stack.P;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by j on 16/01/2016.
 */
public class BoxLocation extends P {

    String o;
    String b;

    public BoxLocation() {
        this.priority = 2;
        // empty constructor to leave the variables undefined

        this.type = "BoxLocation";
        this.o = null;
        this.b = null;
    }

    public BoxLocation(String b, String o) {

        this.type = "BoxLocation";

        this.priority = 2;
        this.o = o;
        this.b = b;
    }

    @Override
    public boolean isParcialDefined(){
        return this.o == null || this.b == null;
    }

    public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }


    public void setB(String b) {
        this.b = b;
    }

    public String getB() {
        return b;
    }

    @Override
    public String toString() {

        // return super.toString();
        return "BoxLocation(" + this.b + "," + this.o + ")";
    }


    public void setAttr(String str) {
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher match = pattern.matcher(str);
        if (match.find()) {
            String[] methodArgs = (match.group(1)).split(",");
            this.o = methodArgs[1];
            this.b = methodArgs[0];
        }
    }
}
