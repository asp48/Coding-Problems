/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.
*/

/*
Use a min-heap of k elements to store the largest k elements in the given array.
Then the top element of the heap is the required kth largest element.

Time: O(nlogk)
Space: O(k)
*/
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        //Build a min-heap of k elements
        for(int num: nums){
            heap.add(num);
             // make sure at any point heap does not grow over k elements
            if(heap.size()>k){
                heap.poll();
            }
        }
        return heap.poll();
    }
}

/*
Quick Select Algorithm

Time: worst case O(n^2) if array is already sorted, best case O(n), average case O(n)
Space: O(1)
*/
class Solution {
    Random r = new Random();
    
    public int findKthLargest(int[] nums, int k) {
        
        int low = 0, n = nums.length, high;
        high = n-1;
        // kth largest means (n-k)th element in the sorted array.
        k = nums.length-k;
        
        while(low<=high){
            // get the position of A[low] in the sorted array
            int pi = partition(nums, low, high);
            // if we find the required kth position, return the result
            if(pi==k){
                return nums[pi];
            // else, move to the corresponding side as in Binary Search
            } else if(k>pi){
                low = pi+1;
            } else {
                high = pi-1;
            }
        }
        
        return 0;
    }
    
    // returns the position of A[low] in the sorted array.
    private int partition(int[] A, int low, int high){
        /*
        * Choosing always low as the pivot, has performance issue. It gets worst when we have an already sorted array.
        * The solution took 13s(faster than 50%) when submitted on Leetcode.
        *
        * To overcome this, pivot position can be randomized.
        * This updated solution took 1s(faster than 97.5%) in Leetcode.
        */
        int pivotIndex = r.nextInt((high - low) + 1) + low;
        swap(A, low, pivotIndex);
        int pivot = A[low];
        int i = low;
        for(int j=low+1;j<=high;j++){
            if(A[j]<pivot){
                swap(A, ++i, j);
            }
        }
        swap(A, low, i);
        return i;
    }
    
    private void swap(int[] A, int i, int j){
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
}