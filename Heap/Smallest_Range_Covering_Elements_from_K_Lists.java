/*
https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/

You have k lists of sorted integers in non-decreasing order. Find the smallest range that includes at least one number from each of the k lists.
We define the range [a, b] is smaller than range [c, d] if b - a < d - c or a < c if b - a == d - c.

Example 1:
Input: nums = [[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].

Example 2:
Input: nums = [[1,2,3],[1,2,3],[1,2,3]]
Output: [1,1]

Example 3:
Input: nums = [[10,10],[11,11]]
Output: [10,11]

Example 4:
Input: nums = [[10],[11]]
Output: [10,11]

Example 5:
Input: nums = [[1],[2],[3],[4],[5],[6],[7]]
Output: [1,7]
 
Constraints:

nums.length == k
1 <= k <= 3500
1 <= nums[i].length <= 50
-105 <= nums[i][j] <= 105
nums[i] is sorted in non-decreasing order.
*/

/*
The solution is similar to using heap for merging m-sorted arrays.
*/
class Node{
    int arrayIndex;
    int elementIndex;
    
    public Node(int arrayIndex, int elementIndex){
        this.arrayIndex = arrayIndex;
        this.elementIndex = elementIndex;
    }
}
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>((n1, n2)->(nums.get(n1.arrayIndex).get(n1.elementIndex) - nums.get(n2.arrayIndex).get(n2.elementIndex)));
        
        int currentMax = Integer.MIN_VALUE;
        // add first element from each list into minHeap
        for(int i=0;i<nums.size();i++){
            if(nums.get(i).size()>0){
                minHeap.add(new Node(i, 0));
                // track current max element
                currentMax = Math.max(currentMax, nums.get(i).get(0));
            }
        }
        int startRange = -1, endRange=-1;
        while(!minHeap.isEmpty()){
            Node top = minHeap.poll();
            int topValue = nums.get(top.arrayIndex).get(top.elementIndex);
            // check if difference between currentMax and min element from heap is less than the current range
            if(startRange==-1 || ((currentMax - topValue) < (endRange - startRange))){
                startRange = topValue;
                endRange = currentMax;
            }
            // check if there is a next element in the array which had the top element.
            if(top.elementIndex+1<nums.get(top.arrayIndex).size()){
                int nextElement = nums.get(top.arrayIndex).get(top.elementIndex+1); 
                // update current max
                if(currentMax < nextElement){
                    currentMax = nextElement;
                }
                // add next element to the minHeap
                minHeap.add(new Node(top.arrayIndex, top.elementIndex+1));
            } else {
                // there is no next element, range cannot go smaller than the current range, so break.
                break;
            }
        }
        return new int[]{startRange, endRange};
    }
}