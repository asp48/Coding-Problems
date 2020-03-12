import java.util.*;

/*
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
*/

/*
Same logic as Missing First Positive
It is given that a number may repeat atmost twice.
After applying cyclic sort, one instance will be at its right location and another at random position.
Answer is basically list of all such numbers.
*/
//5 ms, faster than 96.19%
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        int i = 0;
        while(i<n){
            if(nums[i]!=nums[nums[i]-1]){
                int t = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = t;
            } else{
                i++;
            }
        }
        List<Integer> result = new ArrayList<>();
        for(i=0;i<n;i++){
            // a number which is not at the right position but a duplicate of it is at the correct position.
            if(i!=nums[i]-1 && nums[nums[i]-1] == nums[i]){
                result.add(nums[i]);
            }
        }
        return result;
    }
}
/*
Same logic used to solve First Missing Positive by marking the visited entries.
A[i] is marked as visited by storing A[A[i]-1] = -A[A[i]-1]. 
If at any point, we are referring back to an already visited number, that implies current number is a repetion of that visited number.
So we add current number to the result.

Sample I/O
I: [4,3,2,7,8,2,3,1]
O: 
Flow:
4 3 2 -7 8 2 3 1 []
4 3 -2 -7 8 2 3 1 []
4 -3 -2 -7 8 2 3 1 []
4 -3 -2 -7 8 2 -3 1 []
4 -3 -2 -7 8 2 -3 -1 []
4 -3 -2 -7 8 2 -3 -1 [2]
4 -3 -2 -7 8 2 -3 -1 [2, 3]
-4 -3 -2 -7 8 2 -3 -1 [2, 3]
Answer:
[2,3]
*/
class Solution2 {
    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<n;i++){
            int curr = Math.abs(nums[i]);
            if(nums[curr-1]<0) {
                result.add(curr);
            } else{
                nums[curr-1] *= -1;
            }
        }
        return result;
    }
}