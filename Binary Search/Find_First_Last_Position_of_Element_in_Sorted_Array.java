/*
https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
Your algorithm's runtime complexity must be in the order of O(log n).
If the target is not found in the array, return [-1, -1].

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
*/

/*
Apply binary search to find position of the target and then do linear scan to find the range.

Time: O(logn + k) where k is the length of the range. Worst case O(n) when all elements in the array are same
Space: O(1)
*/
class Solution {
    public int[] searchRange(int[] nums, int target) {
        
        int low = 0, n = nums.length, high = n-1, mid;
        int start=-1, end=-1;
        while(low<=high){
            mid = low + (high-low)/2;
            if(nums[mid]==target){
                start=mid-1;
                end=mid+1;
                while(start>=0 && nums[start]==target){
                    start--;
                }
                while(end<n && nums[end]==target){
                    end++;
                }
                start++;
                end--;
                break;
            } else if(target<nums[mid]){
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[]{start, end};
    }
}

/*
Above solution, which has linear time complexity in the worst case, can be optimized to logarithmic time.
Apply Binary search two times to find the leftmost and righmost target element.

Time: O(2*log n)
Space: O(1)
*/
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int start = findExtremePosition(nums, target, true);
        // if start is -1, it implies target is not found, hence no need to perform binary search again.
        int end = start!=-1 ? findExtremePosition(nums, target, false) : -1;
        return new int[]{start, end};
    }
    
    // Returns the leftmost or rightmost index of the target element based on whether boolean parameter left is set to true or false respectively.
    private int findExtremePosition(int[] nums, int target, boolean left){
        int low = 0, high = nums.length-1, mid, index=-1;
        while(low<=high){
            mid = low + (high-low)/2;
            // target is found
            if(target==nums[mid]){
                // store the index
                index = mid;
                // continue moving left to find leftmost index of target element
                if(left){
                    high = mid-1;
                // continue moving right to find rightmost index of target element
                }else {
                    low = mid+1;
                }
            } else if(target<nums[mid]) {
                high = mid-1;
            } else {
                low = mid+1;
            }
        }
        return index;
    }
}