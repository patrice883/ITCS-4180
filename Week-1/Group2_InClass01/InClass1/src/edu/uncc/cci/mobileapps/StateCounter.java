package edu.uncc.cci.mobileapps;

public class StateCounter implements Comparable<StateCounter>{
    String state;
    Integer count;

    public StateCounter(String state, Integer count) {
        this.state = state;
        this.count = count;
    }

    @Override
    public int compareTo(StateCounter o) {
        return this.count - o.count;
    }

    @Override
    public String toString() {
        return this.state + "\t" + this.count;
    }
}
