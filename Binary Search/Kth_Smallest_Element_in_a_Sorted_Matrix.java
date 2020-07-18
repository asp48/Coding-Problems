
/*
https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note:
You may assume k is always valid, 1 ≤ k ≤ n2.
*/

/*
Using Binary Search,
Pick a middle value and find the number of values smaller than this choosen value. If the count is less than K, move to the right, else move to the left.

Interesting thought!!
Though the middle number may not be present in the given matrix, it will slowly converge and final result is a number that is present in the matrix. Therefore, we cannot stop the binary search, even if we have found a mid value, which is >= to K-1 elements in the matrix.

Time: O(N*log(diff(lastValue, firstVal))) -- Not sure
1 ms, faster than 83.80%
*/
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int low = matrix[0][0];
        int high = matrix[n-1][n-1];
        while(low < high){
            int mid = low + (high-low)/2;
            int count = getCountOfSmallerElements(matrix, mid); 
            if(count<k){
                // Even if we find count==k-1, we cannot exit, because mid may not be the element present in the matrix.
                low = mid+1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // get count of elements smaller than the target element 
    public int getCountOfSmallerElements(int[][] matrix, int target) {
        //start from bottom-left
        int n = matrix.length, count = 0, i = n - 1, j = 0;
        while( i>=0 && j < n){
            // if current elment is greater than the target, move up the rows.
            if(matrix[i][j] > target){
                i--;
            } else {
                /*
                    current element is lesser than or equal to the target
                    count number of elements at the top of current element in the same column.
                */
                count += i+1;
                // move to the right in the same row
                j++;
            }
        }
        return count;
    }
}

/*
Using Heap
Time: O(klogN)
19 ms, faster than 37.78%
*/
class Solution2 {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        // store element indices (i,j) in the priority queue
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b)->(matrix[a[0]][a[1]] - matrix[b[0]][b[1]]));
        // insert all the elements from the first column into the heap
        for(int i=0;i<n;i++){
            heap.add(new int[]{i,0});
        }
        // loop until kth smallest element gets inserted into the heap.
        while(k>1){
            int[] e = heap.poll();
            k--;
            // add next element on the right to the heap
            if(e[1]+1 < n) {
                heap.add(new int[]{e[0], e[1]+1});
            }
        }
        // pop the smallest element from heap which is the required element
        int[] e = heap.poll();
        return matrix[e[0]][e[1]];
    }
}