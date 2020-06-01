import java.util.Arrays;

/*
https://leetcode.com/problems/largest-number/

Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a string instead of an integer.
*/

/*
Time: O(nlogn)
Space: O(n)
*/
class Solution {
    public String largestNumber(int[] nums) {
        // convert to string array, otherwise we cannot sort a primitive int array using a comparator
        String[] arr = Arrays.stream(nums).mapToObj(String::valueOf).toArray(String[]::new);
        // sort with an explicit comparator    
        Arrays.sort(arr, (n1, n2)->{
            // concat in different order and sort in decreasing order
            return (n2+n1).compareTo(n1+n2);
        });
        // if largest number after sort is 0, => number is 0
        if(arr[0].equals("0")){
            return "0";
        }
        // convert array to string
        return String.join("", arr);
    }
}