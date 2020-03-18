/*
Sort a linked list in O(n log n) time using constant space complexity.

Example 1:

Input: 4->2->1->3
Output: 1->2->3->4
Example 2:

Input: -1->5->3->4->0
Output: -1->0->3->4->5
*/

/*
Using merge sort
Time: O(nlogn)
Space: O(1)
*/
class Solution {
    public ListNode sortList(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }   
        ListNode mid = findMiddle(head);
        ListNode list1 = head;
        ListNode list2 = mid.next;
        mid.next = null;
        list1 = sortList(list1);
        list2 = sortList(list2);
        ListNode mNode = new ListNode(0), dummy=mNode;
        while(list1!=null && list2!=null){
            if(list1.val <= list2.val){
                mNode.next = list1;
                list1 = list1.next;
            } else {
                mNode.next = list2;
                list2 = list2.next;
            }
            mNode = mNode.next;
        }
        if(list1==null){
            mNode.next = list2;
        } else {
            mNode.next = list1;
        }
        return dummy.next;
    }
    
    public ListNode findMiddle(ListNode head){
        ListNode slow = head, fast=head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}