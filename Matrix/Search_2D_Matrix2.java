/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
*/
//Recursion, Jumping Diagonally, 5 ms, faster than 99.88%
//ToDo: Complexity?
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m==0) return false;
        int n = matrix[0].length;
        return helper(matrix, target, 0, m-1, 0, n-1);
    }
    
    public boolean helper(int[][] matrix, int target, int i1, int i2, int j1, int j2){
        if(i1>i2 || j1>j2){
            return false;
        }
        int i=i1, j=j1;
        //Checking diagonally
        while(i<=i2 && j<=j2){
            if(matrix[i][j]==target){
                return true;
            } else if(matrix[i][j]>target){
                break;
            }
            i++;
            j++;
        }
        //Narrowing the searach space to two sub matrices.
        return helper(matrix, target, i, i2, j1, j-1) || helper(matrix, target, i1, i-1, j, j2);
    }
}
//Recustion, Binary Search, 8 ms, faster than 23.64%
class Solution2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m==0) return false;
        int n = matrix[0].length;
        return helper(matrix, target, 0, m-1, 0, n-1);
    }
    
    public boolean helper(int[][] matrix, int target, int i1, int i2, int j1, int j2){
        if(i1>i2 || j1>j2){
            return false;
        }
        int midi = i1 + (i2-i1)/2;
        int midj = j1 + (j2-j1)/2;
        int midVal = matrix[midi][midj];
        if(midVal==target){
            return true;
        }
        if(target>midVal){
            return helper(matrix, target, midi+1, i2, j1, midj) || helper(matrix, target, i1, i2, midj+1, j2);
        } else {
            return helper(matrix, target, i1, i2, j1, midj-1)||helper(matrix, target, i1, midi-1, midj, j2);
        }
    }
}
//Iterative, Kind of Binary Search (Not exactly), 5 ms, faster than 99.88%
//time: O(m+n), space: O(1)
class Solution3 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m==0) return false;
        int n = matrix[0].length;
        int r=m-1, c=0;
        while(r>=0 && c<n){
            if(target==matrix[r][c]){
                return true;
            }else if(target>matrix[r][c]){
                c++;
            }else{
                r--;
            }
        }
        return false;
    }
}
//In the above solution, we can also start from r=0 and c=n-1, but not from r=0,c=0 and r=m-1,c=n-1

/*
ToDo:
Better solution available
*/