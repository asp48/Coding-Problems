
/*
https://leetcode.com/problems/increasing-triplet-subsequence/

Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:

Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

Example 1:

Input: [1,2,3,4,5]
Output: true
Example 2:

Input: [5,4,3,2,1]
Output: false

*/

/*
Intuition:
Every element has the potential to be part of the triplet, so cannot ignore any element. That also implies we have to scan the complete array.
While scanning array, keep track of first minimum and second minimum, later at any point, if we find an element greater than second minimum, result is true.
Otherwise, result is false

Time: O(N)
Space: O(1)
*/
class Solution {
    public boolean increasingTriplet(int[] nums) {
        // initialize the minimums with MAX_INT value, and count of increasing subsequence elements observed so far to 0.
        int firstMinimum = Integer.MAX_VALUE, secondMinimum = Integer.MAX_VALUE, count=0;
        for(int num: nums){
            if(num < firstMinimum){
                // set the first minimum
                firstMinimum = num;
                // increase only once for first minimum
                count = (count==0)? count+1: count;
            } else {
                // we have already found 2, and this is the third one, return true
                if(count==2 && num > secondMinimum){
                    return true;
                // we have found a new second minimum
                } else if(num > firstMinimum && num < secondMinimum){
                    secondMinimum = num;
                    // increase only once for second minimum
                    count = (count == 1)? count+1: count;
                }
            }
        }
        // there is no increasing triplet subsequence in the given array
        return false;
    }
}