/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

Note: Do not modify the linked list.

 

Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Floyd's Cycle Detection algorithm
time O(n)
space O(1)
Explaination: https://www.youtube.com/watch?v=LUm2ABqAs1w
*/
class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head==null || head.next==null) return null;
        ListNode p = head;
        ListNode q = head;
        while(q!=null && q.next!=null){
            p = p.next;
            q = q.next.next;
            if(p==q){
                break;
            }
        }
        if(p!=q){
            return null;
        }
        p=head;
        while(p!=q){
            p=p.next;
            q=q.next;
        }
        return p;
    }
}

