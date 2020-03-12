/*
Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:

Given n will always be valid.

Solution: O(n) one pass.
Initialize two pointers with a difference of n;
move both the pointers by one position until second pointer reaches end of the list.
when second list reaches end, delete the element after the first pointer.
handle edge cases;
[1,2] delete 1st, delete 2nd
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        ListNode second = head;
        for(int i=0;i<n;i++){
            second=second.next;
        }
        while(second!=null && second.next!=null){
            first=first.next;
            second=second.next;
        }
        if (second==null && head==first){
            head = first.next;
        } else {
            first.next = first.next.next;
        }
        return head;
    }
}