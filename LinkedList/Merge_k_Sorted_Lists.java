/*
https://leetcode.com/problems/merge-k-sorted-lists/

You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
Merge all the linked-lists into one sorted linked-list and return it.

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
*/

/*
Time: O(N*logK) where N=total number of nodes in K input arrays
Space: O(K), size of min-heap
*/
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((n1, n2)->(n1.val-n2.val));
        
        // add first node from each input list into minHeap
        for(ListNode node: lists){
            if(node!=null){
                minHeap.add(node);
            }
        }
        
        ListNode resultHead=null, resultTail=null;
        while(!minHeap.isEmpty()){
            // get the minimum node from the heap and append to the end of result list
            ListNode minNode = minHeap.poll();
            if(resultHead==null){
                resultHead = minNode;
                resultTail = minNode;
            } else {
                resultTail.next = minNode;
                resultTail = minNode;
            }
            // add minNode's next node to the heap
            if(minNode.next!=null){
                minHeap.add(minNode.next);
            }
        }
        return resultHead;
    }
}