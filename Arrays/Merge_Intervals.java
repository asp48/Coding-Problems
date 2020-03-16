import java.util.*;

/*
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
*/

/*
If we sort based on lower interval, overlapping intervals will settle next to each other.
Then we compare linearly and group overlapping intervals.

Time: O(nlogn + n)
Space: O(1)
*/
class Solution {
    public int[][] merge(int[][] intervals) {
        
        List<int[]> result = new LinkedList<>();
        int i=0, n=intervals.length, start, end;
        
        // sort in the ascending order of lower interval
        Arrays.sort(intervals, (n1,n2)->(n1[0]-n2[0]));
       
        while(i<n){
            start=intervals[i][0];
            end=intervals[i][1];
            
            // find where current merge interval ends
            while(i<n-1 && end>=intervals[i+1][0]){
                end = Math.max(end, intervals[i+1][1]);
                i++;
            }
            // update the result
            result.add(new int[]{start, end});
            i++;
        }
        return result.toArray(new int[result.size()][2]);
    }
}