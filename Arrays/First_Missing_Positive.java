/*
Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1

Using the concept of cyclic sort, if we keep each value at its right position i.e (i == array[i]), then the shortest index which does not hold the right value is the answer.

In the below solution, the right position is i==(array[i]+1) or i-1 == array[i], because since we are dealing with only positives and if 0 is not at its right position, we should not return 0. one way to achieve that is skip 0 along with negative numbers and store 1to n starting from index 0.

time: O(n)
space: O(1)
*/

class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i=0;
        //Ex 3,5,-1,1]
        while(i<n){
            if(nums[i]>0 // skip 0 and negative numbers
            && nums[i] <= n // skip if the value is more than the length of the array, it can not be put to right position
            && nums[i]!=nums[nums[i]-1]) // cyclic sort criteria
            {
                // swap nums[i] and nums[nums[i]-1]
                int t = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i]=t;
            } else{
                i++;
            }
        }
        //After cyclic sort: [1,5,3,-1]
        // find the shortest index that does not contain the right value
        for(i=0;i<n;i++){
            if(i!=nums[i]-1){
                return i+1;
            }
        }
        // If all elements at right position, then n+1 is the first missing positive. 
        return n+1;
    }
}
/*
Intuition:
If we could somehow mark a number as visited in the input array itself, then the first unvisited index is the required answer.

Algorithm:
1. Remove all non-positive numbers by replacing them with a number higher than array length. Since we can't mark a number as visited if it is outside the length of the array, these non positive numbers will not be processed. 
2. Mark array[i] as visited by setting array[array[i]-1] = - array[array[i]-1]. If it is already negative(not originally negative, but marked that way), get the absolute value and mark this value as visited. In general, array[abs(array[i])-1] = - array[abs[array[i]]-1]. 
Here -1 because to eliminate 0 from ending up in answer. We mark 1 as visited by storing a negative number in array[0].
    2.1. Skip if a number is greater than the length of array or it is already visited i.e array[abs(array[i])-1] is negative
3. Index of the first postive number +1 is the required missing positive number. Because a positive number implies it was not marked as visited.
4. If all elements are visited i.e all are negative, then n+1 is the first missing postive.

time: O(n)
space: O(1)
*/
class Solution2 {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i=0;
        // Step 1
        for(i=0;i<n;i++){
            if(nums[i]<=0){
                nums[i]=n+1;
            }
        }
        // Step 2
        for(i=0;i<n;i++){
            int current = Math.abs(nums[i]);
            if(current<=n && nums[current-1]>0){
                nums[current-1] = -nums[current-1];
            } 
            // Else step 2.1
        }
        // Step 3
        for(i=0;i<n;i++){
            if(nums[i]>0){
                return i+1;
            }
        }
        // Step 4
        return n+1;
    }
}