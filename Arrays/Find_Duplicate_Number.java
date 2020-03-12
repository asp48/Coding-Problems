/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Example 1:

Input: [1,3,4,2,2]
Output: 2
Example 2:

Input: [3,1,3,4,2]
Output: 3
Note:

You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.

Intuition:
If we interpret array such that for each pair of index i and value vi, the "next-pointer" value vj is at index vi. In other words, vi is a next pointer between i and vj. Now, the problem is converted into finding start of the loop in a single linked list.
Why so?
1. Since array has more capacity(n+1) than the range of values(1 to n) an element can take, it is fore sure that value vi at index i, is also an index bound inside the array.
2. Since there is always one duplicate number in the given array, it is for sure that there exists a cycle between two duplicate instances in the array when interpreted as list. Hence of start of the loop is the required answer for this problem.

Solution: Applying Floyd's Cycle Detection Algorithm
time: O(n)
space: O(1)
*/

class Solution {
    public int findDuplicate(int[] nums) {
        int fast=0;
        int slow=0;
        while(slow==0 || slow!=fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow=0;
        while(slow!=fast){
            slow=nums[slow];
            fast=nums[fast];
        }
        return slow;
    }
}