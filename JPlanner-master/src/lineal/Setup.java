package lineal;

import lineal.stack.State;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by j on 16/01/2016.
 */
public class Setup {

    private int dimension;
    private String config_file_path;
    private HashMap<String, List> rawConfig;

    public Setup(int level) {
        if (level == 0) {
            this.dimension = 2;
        } else {
            this.dimension = 3;
        }
        this.rawConfig = new HashMap<>();
        this.config_file_path = System.getProperty("user.dir") + "/src/lineal/setup/config." + this.dimension + ".level." + level + ".txt";

        // Boxes
        // Offices
        // InitialState
        // GoalState
    }

    public void load() {
        this.rawConfig = this.loadConfigHashMap();
    }

    public HashMap<String, List> getConfig() {
        return rawConfig;
    }

    public State getState(String state) {
        List<String> strState = this.rawConfig.get(state);
        State s = new State();
        // set the state predicates
        for(String str : strState){
            s.addPre(str);
        }

        return s;
    }

    public HashMap loadConfigHashMap() {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(this.config_file_path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, List<String>> configuration = new HashMap();
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            String currentKey = null; // seek for XXX=YYYY
            while (line != null) {
                line = line.replaceAll("\\s+", ""); // remove empty spaces
                if (line.indexOf("=") != -1) {
                    String[] key_value = line.split("=");
                    currentKey = key_value[0];
                    configuration.put(currentKey, new ArrayList<String>());
                    line = key_value[1];
                }
                String reg = ",";
                if (line.indexOf(";") != -1) {
                    reg = ";";
                }
                for (String item : line.split(reg)) {
                    configuration.get(currentKey).add(item);
                }
                // configuration
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return configuration;
    }

    public HashMap<String, Set<String>> setupOfficeAdjacent() {
        List<String> offices = this.getConfig().get("Offices");
        HashMap<String, Set<String>> adjacent = new HashMap<>();
        int dim = (int) Math.sqrt(offices.size());
        String[][] building = new String[dim][dim];
        int index = 0;
        // hasSetup the building index
        for (int x = 0; x < dim; x++) {
            for (int y = 0; y < dim; y++) {
                building[x][y] = offices.get(index++);
            }
        }
        // check if adjacent
        for (int row = 0; row < dim; row++)
            for (int column = 0; column < dim; column++) {
                String office = building[row][column];
                adjacent.put(office, new HashSet<>());
                try {
                    if (building[row + 1][column] != null) {
                        // office.putAdjacent(building[row + 1][column]);
                        adjacent.get(office).add(building[row+1][column]);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {

                    if (building[row][column + 1] != null) {
                        adjacent.get(office).add(building[row][column + 1]);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    if (building[row - 1][column] != null) {
                        adjacent.get(office).add(building[row - 1][column]);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                try {
                    if (building[row][column - 1] != null) {
                        adjacent.get(office).add(building[row][column - 1]);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }

        return adjacent;
    }

    public static void main(String[] args) {
        System.out.println("Setup");
        Setup s = new Setup(0);
        s.load();
        Map m = s.getConfig();
        System.out.println(m);
        System.out.println("Setup/End");
    }


}
