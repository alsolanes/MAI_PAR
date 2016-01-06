package none;

import none.config.Loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by j on 12/12/2015.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        String keyboard;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        System.out.println("Planning and approximate reasoning 2015-2016");
        System.out.println("X & Y");

        Map<String, Runner> planners = new HashMap<String, Runner>();

        /*
        Adding runners
        Set : collection that contains no duplicated
        List: collection that contains ordered elements
         */
        // planners.put(RunnerLinear.class.getSimpleName(), new RunnerLinear());
        planners.put(RunnerDepthFirst.class.getSimpleName(), new RunnerDepthFirst());
        planners.put(RunnerRestriction.class.getSimpleName(), new RunnerRestriction());

        Loader configuration = new Loader(0);

        try {
            //while (true) {
                ArrayList options = printMenu(planners.keySet());
                System.out.println("Select planner: ");
                // keyboard = br.readLine();
                keyboard = "0";
                System.out.println("... " + keyboard + " --> ");
                if (Integer.parseInt(keyboard) < options.size()  && Integer.parseInt(keyboard)>= 0) {
                    planners.get(options.get(Integer.parseInt(keyboard))).execute(configuration);
                } else {
                    System.out.println("wrong option.");
                }
            //}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> printMenu(Set<String> options) {
        ArrayList<String> result = new ArrayList<>();
        int i = 0;
        System.out.println("\n\n...");
        for (String key : options) {
            result.add(i, key);
            System.out.println(i++ + " : " + key);
        }
        return result;
    }
}
