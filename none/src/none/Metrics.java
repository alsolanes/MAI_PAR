package none;

import java.util.*;

/**
 * Created by j on 12/12/2015.
 */
public class Metrics {

    long startTime;

    Map<Long, String> tracker = new HashMap<>();

    /**
     * Instance the invocation time of the metric collector
     */
    public Metrics() {
        this.resetTimer();
    }

    /**
     *
     * @param note
     */
    public void keepTrack(String note) {
        tracker.put(System.currentTimeMillis() - this.startTime, note);
    }

    /**
     *
     * @return
     */
    public Collection getTracker(){
        return this.tracker.values();
    }


    public long getElapsed(){
        return System.currentTimeMillis() - this.startTime;

    }

    public void resetTimer(){
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {

        String result = "";
        for(Long key: this.tracker.keySet()){
            result += "\ntime: ["+key +"] \t - " + this.tracker.get(key);
        }
        return result;
    }
}
