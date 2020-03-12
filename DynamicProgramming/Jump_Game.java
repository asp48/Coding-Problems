/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.

Solution:
Recurion, Backtracking

Optiomization:
1. Jump maximum first, then descend till 1
2. Set value to 0, for the index from where destination is unreachable. A way of marking it as no way forward.


*/

class Solution {
    public boolean canJump(int[] nums) {
            return canJumpToEnd(nums, 0);
    }
    
    public Boolean canJumpToEnd(int[] nums, int position){
        if (position == nums.length-1){
            return true;
        }
        if (position >= nums.length) {
            return false;
        }
        for(int i=nums[position];i>0;i--){
            Boolean possible = canJumpToEnd(nums, position+i);
            if (possible){
                return true;
            }
            nums[position]=0;
        }
        return false;
    }
    
}

/*
Botton Up Approach.
DP O(n^2)
*/
class Solution2 {
    public boolean canJump(int[] nums) {
        nums[nums.length-1] = 1; // >0
        for(int i=nums.length-2; i>=0; i--){
            Boolean possible = false;
            if (nums[i] > 0){
                int farJump = Math.min(i+nums[i], nums.length-1);
                for(int j=i+1;j<=farJump;j++){
                    if (nums[j] != 0){
                        possible = true;
                        break;
                    }
                }
            }
            if (!possible) {
                nums[i]=0;
            }
        }
        return nums[0] > 0;
    }
}

/*
Greedy Method
All we have to do is keep track of possible index from right. Then see if it is possible to reach that index from current position.
Time: O(n)
space: O(1)
*/
class Solution3 {
    public boolean canJump(int[] nums) {
        int possibleIndex = nums.length-1;
        for(int i=nums.length-2; i>=0; i--){
           if (i+nums[i]>=possibleIndex){
                 possibleIndex = i;   
            } 
        }
        return possibleIndex == 0;
    }
}