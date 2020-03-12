package LinkedList;

/*
Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true

Algorithm:
1. Reverse the first half of the Linked List. This can be achieved using two pointers Slow and Fast. For each increment of Slow, Fast increments by two. As a result distance covered by Slow is half of the distance covered by Fast. Hence when Fast reaches the end, slow will be at half of the linkedlist.
2. Compare first half with second half until there is a mismatch. Skip the middle element in case of odd length.
3. If end of the list is reached and there is no mismatch, return true, otherwise return false.

The below solution does in place reverse. It can be reversed back if it is required to retain the original list.
*/
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        ListNode prevNode = null;
        //reversing first half of Linked List
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            ListNode tmp = slow.next;
            slow.next = prevNode;
            prevNode = slow;
            slow = tmp;
        }
        
        //Skipping middle element in case of odd length
        if(fast!=null){
            slow = slow.next;
        }
        
        //compare until there is a mismatch
        while(prevNode!=null && prevNode.val==slow.val){
            prevNode=prevNode.next;
            slow=slow.next;
        }
        
        //if end of the list is reached, return true, otherwise false
        return prevNode==null;
    }
}