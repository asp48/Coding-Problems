/*
https://leetcode.com/problems/spiral-matrix/

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
*/

/*
Intuition
Iterating layer by layer in spiral order

Time: O(m*n) m=number of rows, c=number of columns
Space: O(1)
*/
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        List<Integer> result = new ArrayList<>();
        if(m==0){
            return result;
        }
        int n= matrix[0].length, r=0, c=0;
        while(r <= m-r-1 && c <= n-c-1) {
            // top row
            for(int i=c;i<n-c;i++){
                result.add(matrix[r][i]);
            }
            // right column
            for(int i=r+1;i<m-r;i++){
                result.add(matrix[i][n-c-1]);
            }
            // check if there are two more sides
            if(r < m-r-1 && c < n-c-1){
                // bottom row
                for(int i=n-c-2;i>=c;i--){
                    result.add(matrix[m-r-1][i]);
                }
                // left column
                for(int i=m-r-2;i>r;i--){
                    result.add(matrix[i][c]);
                }
            }
            // move diagonally down, to the next spiral layer
            r++;
            c++;
        }
        return result;
    }
}