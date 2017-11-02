package edu.uncc.cci.mobileapps;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;

/**
 * Assignment #: InClass1 | Question #2
 * File Name: MainPart2.java
 * @author Aileen Benedict & Devansh Desai
 * @version August 28th, 2017
 */

public class MainPart2 {
    /*
    * Question 2:
    * - In this question you will use the Data.users array that includes
    * a list of users. Formatted as : firstname,lastname,age,email,gender,city,state
    * - Create a User class that should parse all the parameters for each user.
    * - The goal is to count the number of users living each state.
    * - Print out the list of State, Count order in ascending order by count.
    * */


    public static void main(String[] args) {

        //HashMap<String, User> users = new HashMap<String, User>();

        HashMap<String, Integer> counter = new HashMap<String, Integer>(); // use to count

        /*From StackOverFlow
        ValueComparator bvc = new ValueComparator(counter);
        TreeMap<String, Integer> sorted = new TreeMap<String, Integer>(bvc);
        */

        //Fill the HashMap with users from the Data
        for (String str : Data.users) {
            User u = new User(str);
            //users.put(u.getState(), u);

            if(!(counter.keySet().contains(u.getState()))){
                counter.put(u.getState(), 1);
            }
            else{
                counter.replace(u.getState(), counter.get(u.getState()) + 1);
            }

        }

        // My inefficient code :'(
        ArrayList<StateCounter> sorting = new ArrayList<StateCounter>();
        // for each key in hashmap...
        for(Map.Entry<String, Integer> entry : counter.entrySet()){
            sorting.add(new StateCounter(entry.getKey(), entry.getValue()));
        }
        Collections.sort(sorting);

        System.out.println(sorting.toString());


        /* Trying to do HashMap flippy thing. WIP :')
        HashMap<Integer, String> sorted = new HashMap<Integer, String>();
        for(Map.Entry<String, Integer> entry : counter.entrySet()){
            sorted.put(entry.getValue(), entry.getKey());
        }

        System.out.println(sorted);
        */

        /*
        Object[] keys = sorted.keySet().toArray();
        Arrays.sort(keys);
        for(int i = 0;i < sorted.values().size();i++){
            System.out.println(sorted.get(keys) + "\t" + keys[i]);
        }
        */

        /* From TA/StackOverFlow
        sorted.putAll(counter);
        System.out.println(sorted);
        */



    } // End Main

    /* From StackOverFlow
    static class ValueComparator implements Comparator<Integer> {
        Map<String, Integer> base;

        public ValueComparator(Map<String, Integer> base) {
            this.base = base;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            if(base.get(o1) >= base.get(o2)){
                return -1;
            }
            else{
                return 1;
            }
        }
    }
    */

} // End Class