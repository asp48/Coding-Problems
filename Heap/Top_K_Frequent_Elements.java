import java.util.*;

/*
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
*/
/*
Intuition:
A data structure which gives fastest access to elements ordered by their frequencies. HEAP.

Algorithm:
    1. Create a hashMap to store element->frequency mapping.
    2. Build a heap by inserting each key from hashMap such that heap size is always less than k. With this approach, we will have top K frequent elements in the heap and inserting will not take more than logK time. 
    3. Return all the elements in the heap as result.
Note: PriorityQueue is made to store the elements in the ascending order of frequency. The least frequent element will be at the top of the heap. So if we remove top element from the heap one by one, we will get an array of k frequent elements in the increasing order of frequency. We have to reverse the array to get most frequent elements towards the start of the array.

time: O(n+nlogk+klogk+k) = O(nlogk)
space: O(n+k) = O(n)
*/
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // Build a hash map of element->frequency
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num,0)+1);
        }

        //Build a min-heap of k elements
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b)->map.get(a)-map.get(b));
        for(int num: map.keySet()){
            heap.add(num);
            // make sure at any point heap does not grow over k elements.
            if(heap.size()>k){
                heap.poll();
            }
        }
        
        //Poll the heap to build the result array.
        List<Integer> result = new LinkedList<>();
        while(!heap.isEmpty()){
            result.add(heap.poll());    
        }
        //Optional: If descending order of frequency is required.
        //Collections.reverse(result);
        return result;
    }
}

/*
quick-select algorithm: Helps to find kth largest/smallest without sorting the array. This is the main logic behind quick sort.
time: worst case O(n^2) if array is already sorted, best case O(n), average case O(n)

Intuition:
Using the quick select, if we can find the (n-k+1)th smallest element(based on frequency) in the array, all the elements on the right form the required top k frequenet elements of the array. Unlike heap, this approach does not produce the result in a sorted order of frequency.

Algorithm:
    1. Create a hashMap to store element->frequency mapping.
    2. Find (n-k+1)th smallest element.
    3. Return all the elements on the right of n-k+1 as result.
*/
class Solution2 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // Build a hash map of element->frequency
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums){
            map.put(num, map.getOrDefault(num,0)+1);
        }

        // Build an associative array of value and frequency for the elements in the map.
        Entry[] A = new Entry[map.size()];
        int i=0;
        for(int num: map.keySet()){
            A[i++] = new Entry(num, map.get(num));
        }

        // Initialize variables to apply quick-select
        int s=0, e=A.length-1, n = A.length;
        k=n-k; // No +1 because array starts from index 0. 
        
        List<Integer> result = new LinkedList<>();
        while(s<=e){
          // get the position of A[s] in the sorted array
          int pi = partition(A, s, e);  
          // if we find the required kth position, return the result
          if(pi==k || pi==k-1){
              for(int m=k;m<n;m++){
                  result.add(A[m].num);
              }
              break;
          // else, move to the corresponding side as in Binary Search
          } else if(k>pi){
              s = pi+1;
          } else {
              e = pi-1;
          }
        }
        return result;
    }
    
    // returns the position of A[s] in the sorted array.
    public int partition(Entry[] A, int s, int e){
        int pivot=A[s].count;
        int i=s;
        for(int j=s+1;j<=e;j++){
            if(A[j].count<=pivot){
                swap(A, ++i, j);
            }
        }
        swap(A, s, i);
        return i;
    }
    
    public void swap(Entry[] A, int i, int j){
        Entry t = A[j];
        A[j] = A[i];
        A[i] = t;
    }
    
    public class Entry{
        int num, count;
        
        public Entry(int num, int count){
            this.num = num;
            this.count= count;
        }
    }
}



