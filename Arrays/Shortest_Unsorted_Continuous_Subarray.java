/*
Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
*/

/*
Intuition
An unsorted array is basically elements at the wrong positions compared to what their positions would have been in a sorted version of the same array. If we know the correct position of each element in the sorted version of the complete array, then the leftmost element which is at the incorrect position marks the starting index of required subarray. Similarly the righmost element which is at the incorrect position marks the end of required subarray.

Approach 1 :  Brute Force
Take each possible subarray (i,j) and check if (0,i-1) is sorted and less than min of (i,j), similarly (j,n-1) is sorted and greater than max(i,j)
Time Complexity: O(n^3), (n^2) for generating subarrays and (n) for checking conditions for each subarray
Space Complexity: O(1)

Approach 2 : Optmized Brute Force
for each i from 0 to n-1
    for each j from i+1 to n-1
        if array[i] > array[j] // implies elements are in wrong positions
            find min incorrect position
            find max incorrect position
ans = max incorrect - min incorrect + 1 // +1 because boundary elements are inclusive
Time Complexity: O(n^2)
Space Complexity; O(1)

Approach 3 : Using sorting
Compare given array with sorted version of the same array
ans = last mismatch - first mismatch
Time Complexity: O(nlogn)
Space Complexity: O(n), copy of original is used for sorting

Approach 4: Using Stack
for each i from 0 to n-1
    if array[i] > stack.peek()
        stack.push(i)  // follows ascending order
    else 
        while stack is not empty && array[i] < stack.peek()
            k = stack.pop()
            start = min(k, start) // find minimum such k
        stack.push(i)  // put the current index in its right position
Use the same above logic to find end
ans = end - start + 1 
Time Complexity: O(2*n) looped two times to find start and end
Space Complexity: O(n) Stack size may grow at max n

Approach 5: Optimized version of stack solution, with no extra space
for i from 1 to n-1
    if array[i] < array[i-1]   // wrong order, incorrect position
        min = find min(array, i, n-1) // find min starting from i to end of array
        start = find correct position of min within (0, i) 
        // array to the left of i is already sorted, so the correct position is k-1 where k is the index of first element > min
Use the same above logic to find end
ans = end-start+1
Time Complexity: O(4*n), 2*(n for finding min/max + n for finding correct position of min/max)
Space Complexity: O(1)
*/
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int start=0, end=0, n=nums.length, min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
        for(int i=1;i<n;i++){
            if(nums[i]<nums[i-1]){
                for(int j=i;j<n;j++){
                    min = Math.min(nums[j], min);
                }
                int k=0;
                while(nums[k]<=min){
                    k++;
                }
                start=k;
                break;
            }
        }
        
        for(int i=n-2;i>=0;i--){
            if(nums[i]>nums[i+1]){
                for(int j=i;j>=0;j--){
                    max = Math.max(nums[j], max);
                }
                int k=nums.length-1;
                while(nums[k]>=max){
                    k--;
                }
                end=k;
                break;
            }
        }
        
        return end-start==0? 0: end-start+1;
    }
}