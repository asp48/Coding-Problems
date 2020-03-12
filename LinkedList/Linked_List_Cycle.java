/*
Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the second node.
*/
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
class Solution1 {
    public boolean hasCycle(ListNode head) {
        if(head==null) {
            return false;
        }
        ListNode p1=head;
        ListNode p2=head.next;
        while(p1!=p2){
            if(p2==null || p2.next==null){
                return false;
            }
            p1=p1.next;
            p2=p2.next.next;
        }
        return true;
    }
}