/*
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4
*/
/*
Similar to merging two sorted arrays during merge sort.

Time: O(min(m,n))
Space: O(1)
*/
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // mNode points to last merged node and head points to first node of the merged list
        ListNode mNode = new ListNode(0), head = mNode;
        
        // Both the lists are non-empty
        while(l1!=null && l2!=null){
            if(l1.val < l2.val){
                mNode.next = l1;
                l1 = l1.next;
            } else {
                mNode.next = l2;
                l2 = l2.next;
            }
            mNode = mNode.next;
        }
        
        // One of the lists is empty
        if(l1!=null){
            mNode.next = l1;
        } else {
            mNode.next = l2;
        }
        return head.next;
    }
}