import java.util.*;

/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

O(3n) --> O(n) 
Use a set to hold all the elements in the input array. Duplicates get eliminated in the process.
Iterate the set until you reach a number whose previous number is not in the set.
Loop from that number incrementing by 1 until u no longer find the number in the set.
Calculate local optimals and then find the global optimal.
*/
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> s = new HashSet<>();
        int cur_len, max_len=0;
        for(int n:nums){
            s.add(n);
        }
        for(int num: s){
            if(s.contains(num-1)) {
                continue;
            }
            cur_len=1;
            while(s.contains(++num)){
                cur_len++;
            }
            if(cur_len>max_len){
                max_len=cur_len;
            }
        }
        return max_len;
    }
}