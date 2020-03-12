/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
If you observe any row and its upper rows, it looks like a histogram. Bars of one's.
So each row will produce a histogram chart. Largest rectangle of one's is nothing but maximum area rectangle in the histogram.
Now, Problem is reduced to finding maximum area among n Histograms.

Time Complexity
O(n^2)
calculating max area in a histogram is O(n)
*/

class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if(m==0) return 0;
        int n = matrix[0].length;
        int[] heights = new int[n];
        int maxArea=0;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                heights[j] = matrix[i][j]=='1'?heights[j]+1:0;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }
    
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if(n==0) return 0;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = -1;
        right[n-1] = n;
        int maxArea = 0;
        for(int i=1;i<n;i++){
            int temp=i-1;
            while(temp>=0 && heights[temp]>=heights[i]){
                temp=left[temp];                
            }
            left[i]=temp;
        }
        for(int i=n-2;i>=0;i--){
            int temp=i+1;
            while(temp<n && heights[temp]>=heights[i]){
                temp=right[temp];                
            }
            right[i]=temp;
        }
        for(int i=0;i<n;i++){
            maxArea = Math.max(maxArea, heights[i]*(right[i]-left[i]-1));  
        }
        return maxArea;
    }
}