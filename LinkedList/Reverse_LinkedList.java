package LinkedList;

/*
Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
*/

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

//Recursion, time:O(n), space:O(n)
class Solution {
    ListNode newHead; //Extra global node to store head of reversed Linked List.
    public ListNode reverseList(ListNode head) {
        newHead=null;
        helper(head);
        return newHead;
    }
    
    public ListNode helper(ListNode curNode){
        if(curNode==null){
            return null;
        }
        ListNode prevNode = helper(curNode.next);
        if(prevNode==null){
            newHead=curNode;
        }else{
            prevNode.next=curNode;
            curNode.next=null;
        }
        return curNode;
    }
}

//Recursion, time:O(n), space:O(n)
class Solution2 {
    public ListNode reverseList(ListNode head) {
        return helper(head, null);
    }
    
    public ListNode helper(ListNode curNode, ListNode prevNode){
        if(curNode==null){
            return prevNode;
        }
        ListNode tmp = curNode.next;
        curNode.next=prevNode;
        return helper(tmp, curNode);
    }
}

//Iterative, time:O(n), space:O(1)
class Solution3 {
    public ListNode reverseList(ListNode head) {
        ListNode prevNode = null;
        while(head!=null){
            ListNode tmp = head.next;
            head.next=prevNode;
            prevNode=head;
            head=tmp;
        }
        return prevNode;
    }
}