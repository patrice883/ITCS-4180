package edu.uncc.cci.mobileapps;

//import java.util.HashSet;
import java.util.*;

/**
 * Assignment #: InClass1 | Question #3
 * File Name: MainPart3.java
 * @author Aileen Benedict & Devansh Desai
 * @version August 28th, 2017
 */

public class MainPart3 {
    /*
    * Question 3:
    * - This is a simple programming question that focuses on finding the
    * longest increasing subarray. Given the array A = {1,2,3,2,5,2,4,6,7} the
    * longest increasing subarray is {2,4,6,7}, note that the values have to be
    * contiguous.
    * */

    public static void main(String[] args) {
        //example call
        //int[] input = {}; // output {}
        //int[] input = {1}; // output {1}
        //int[] input = {1,2,3,4}; // output {1,2,3,4}
        //int[] input = {1,2,3,4,4,4,4,4,5,6}; // output {1,2,3,4}
        //int[] input = {1,2,3,-1,4,5,8,20,25,1,1,4,6}; // output {-1,4,5,8,20,25}
        //int[] input = {1,2,3,1,1,1,2,3,4,1,1,2,4,6,7}; // output{1,2,4,6,7}
        int[] input = {1,2,3,2,5,2,4,6,7}; // output {2,4,6,7}
        int[] result = printLongestSequence(input);

        System.out.println(Arrays.toString(result));
    }

    public static int[] printLongestSequence(int[] input){
        int[] result = {};

        /*
        // If null or size 0, return right away
        if(input.length == 0 || input == null){
            return result;
        }
        */


        int currentIndex = 0;

        while(currentIndex < input.length - 1){
            ArrayList<Integer> current = new ArrayList<Integer>();
            current.add(input[currentIndex]);

            // Add next input element to ArrayList if it is greater than
            // the previous one. Else, break out of loop.
            int i = currentIndex + 1;
            while(i < input.length && input[i] > current.get(i-1)){

                current.add(input[i]);
                i++;

            }

            // If the current array list is larger than the result array,
            // update the result array
            if(current.size() > result.length){
                result = new int[current.size()];

                for(int j = 0; j < result.length; j++){
                   result[j] = current.get(j).intValue();
                }
            }

            currentIndex += 1;
        }

        return result;
    }
}