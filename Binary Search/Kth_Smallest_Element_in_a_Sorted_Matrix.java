
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

Time: O(N*log(diff(lastValue, firstVal)))
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
16 ms, faster than 36.41%
*/
class Node{
    int row;
    int col;
    public Node(int row, int col){
        this.row=row;
        this.col=col;
    }
}
class Solution2 {
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Node> minHeap = new PriorityQueue<>((n1,n2)->(matrix[n1.row][n1.col]-matrix[n2.row][n2.col]));
        
        for(int i=0;i<matrix.length && i<k;i++){
            minHeap.add(new Node(i,0));
        }
        int count=0;
        while(!minHeap.isEmpty()){
            Node node = minHeap.poll();
            if (++count == k){
                return matrix[node.row][node.col];
            }
            if(node.col+1<matrix[node.row].length){
                minHeap.add(new Node(node.row, node.col+1));
            }
        }
        return -1;
    }
}

/*
In the solution1, it is difficult to understand how 'low' finally converges to an element in the matrix.
Here is a more understandable version of solution1.

Time: O(N*log(max-min))
Space: O(1)
*/
class Solution3 {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length, low = matrix[0][0], high = matrix[n-1][n-1];
        // if we continue, event after low==high, then it will go into an infinite loop.
        // Note that, here we are dealing with values, not indices.
        while(low<high){
            int mid = low + (high-low)/2;
            int[] smallLargePair = {matrix[0][0], matrix[n-1][n-1]};
            // get count of elements smaller than or equal to mid
            int count = getSmallerNumbersCount(matrix, mid, smallLargePair);
            // we have k elements less than or equal to mid
            if(count==k){
                // return the largest number smaller than or equal to mid
                return smallLargePair[0];
            } else if(count<k){
                // explore bottom right, set low to smallest number greater than mid
                low = smallLargePair[1];
            } else {
                // explore top left, set high to largest number smaller than or equal to mid 
                high = smallLargePair[0];
            }
        }
        // return low if low==high. This happens when abs(k-count) are all same numbers.
        return low;
    }
    
    public int getSmallerNumbersCount(int[][] matrix, int target, int[] smallLargePair){
        int n = matrix.length, i=n-1, j=0, count=0;
        while(i>=0 && j<n){
            if(matrix[i][j]>target){
                // store the smallest number in the matrix that is greater than the target
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[i][j]);
                i--;
            } else {
                count+=(i+1);
                // store the largest number in the matrix that is smaller than the target
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[i][j]);
                j++;
            }
        }
        return count;
    }
}