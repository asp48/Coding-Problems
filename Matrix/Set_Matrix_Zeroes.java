/*
https://leetcode.com/problems/set-matrix-zeroes/

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:

Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
*/

/*
Solution 1:
The first solution that comes to mind is have two arrays, one for column and one for row.
While traversing each element, if element is 0, set row[i]=0 and column[j]=0;
Iterate over these two arrays separately and set corresponding row/column to 0.
Time: O(M*N), Space: O(M+N)

Solution 2:
To achieve constant space, immediately after we find a zero element, set all non zero elements in that row and column to MAX_VAL. We cannot set to 0 becuase we won't be able to distinguis whether it was originally 0 or we set it to 0.
We iterate over the matrix again, this time we will replace all MAX_VAL with 0.
Time: O(M*N(M+N)), Space: O(1)

Though solution 2 is constant space, it is extremely slow. But it helps us in reaching to optimal solution.

Solution 3:
Instead of marking the complete row/column, we can just mark in the first cell of that row/column.
Thus first column and first row act as two arrays that we used in the first solution.

The catch is that for (0,0), the first cell in column is same as first cell in row. 
Ex: Lets say matrix[0][3] is 0, and we update matrix[0][0] as 0.
Later, when we are iterating for the second time, you can not figure out whether to set complete 0th row/0th column.
We should only set 0th row to 0. Not 0th column.
Similarly if matrix[3[0] is 0, we have to set only 0th column and not 0th row.

This special case can be solved by using a boolean variable zeroCol, which if set to true, it means 0th column should be set to 0. In the above two examples, for first one, we set matrix[0][0] to 0 and zeroCol as false, indicating that only 0th row should be set to 0. For the second example, we will not touch matrix[0][0] and we will set zeroCol to true, indicating only 0th column should be set to 0.

Time: O(M*N)
Space: O(1)
*/
class Solution {
    public void setZeroes(int[][] matrix) {
        Boolean zeroCol = false;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(j==0){
                    // for 0th column, don't update matrix[0][0]
                    // set zeroCol to true. Once it is set to true, it should remain true. No changing back.
                    zeroCol = matrix[i][0]==0?true:zeroCol;
                } else if(matrix[i][j]==0){
                    // set first cell of ith row and jth column to 0
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }
        // ignore first row, as it has valuable information. Set that to 0 later based on whether matrix[0][0] is 0 or not.
        // start from matrix[1][1]
        for(int i=1;i<matrix.length;i++){
            for(int j=1;j<matrix[i].length;j++){
                if(matrix[i][0]==0 || matrix[0][j]==0){
                    matrix[i][j] = 0;
                }
            }
        }
        // set 0th row to 0, if matrix[0][0] is 0, which may be either by marking or it was originally 0.
        if(matrix[0][0]==0){
            for(int i=0;i<matrix[0].length;i++){
                matrix[0][i] = 0;
            } 
        }
        // set 0th column to 0, if zeroCol is set to true.
        if (zeroCol){
            for(int i=0;i<matrix.length;i++){
                matrix[i][0] = 0;
            }
        }
    }
}