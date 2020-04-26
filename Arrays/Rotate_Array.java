/*
https://leetcode.com/problems/rotate-array/

Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
*/

/*
Intuition:
If we can find out the required correct position of each element, then we can rotate the array in one pass.
But while we place the element at its correct position, original element at that position has to be moved out to create a placeholder. Instead of moving original element to random position, we can put it in its correct position and so on in a cyclic manner.

Number of cycles = gcd(n,k)

Time: O(n)
Space: O(1)
*/
class Solution {
    public void rotate(int[] nums, int k) {
        int count = 0, n =nums.length;
        k = k% n;
        for(int i=0;count < n;i++){
            int prev=nums[i];
            int current = i;
            int next;
            do{
                next = (current+k)%n;
                int tmp = nums[next];
                nums[next] = prev;
                prev = tmp;
                current = next;
                count++;
            } while(i!=next);
        }
    }
}