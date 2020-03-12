/*
Write a program to find the node at which the intersection of two singly linked lists begins.

Example 1:
Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
Output: Reference of the node with value = 8
Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before 
the intersected node in B.

Solution: time O(n) and space O(1)

Step1:
    lenA = length of list A
    lenB = length of list B
Step2:
    calculate the absolute difference.
Step3:
    Initialize two pointers pA and pB, to the head of A and B respectively.
    In the longer list, move the pointer upto diff number of nodes.
    This is because intersection cannot happen before the diff number of nodes in longer list. If you have two lists, one bigger and smaller, imagine minimum number of nodes to be skipped in longer list to start with bare minimal cases, i.e no intersection and intersection with the first node of smaller list.
    Ex lenA=2, lenB=5
    Let i, j be index of intersection node in list A and B respectively.
    Here is the exhaustive possibilities of intersection for the given lengths.
    [2, 3] and [0,1,5,6,9] // no intersection
    [2, 3] and [0,1,5,2,3] // i = 0, j = 3
    [2, 3] and [0,1,5,7,3] // i = 1, j = 4
    Intersection can happen only from j=3 in list B. That explains why we have to skip diff number of nodes in longer list under step3.
Step4:
    Compare two pointers, moving one node at a time, until one of the pointer reaches the end of the list.
    If there is a match, return the node, otherwise null ( no intersection)

The above solution does involve multiple passes over the lists and hence multiple loops. The solution can be simplified enough to write a compact code of only one loop. The logic behind the solution remains the same.

Steps:
Maintain two pointers pA and pB initialized at the head of A and B, respectively. Then let them both traverse through the lists, one node at a time.
When pA reaches the end of a list, then redirect it to the head of B. similarly when pB reaches the end of a list, redirect it the head of A.
If at any point pA meets pB, then pA/pB is the intersection node.
Keep track of number of times either of these pointers reach to the end of list. If it exceeds 2, that means there is no intersection and loop should be terminated.

In the later solution, when c=2, or when both the pointers reach the end and get reset, it reaches the same state as we expected in former solution, i.e short list pointer is pointing to the head and long list pointer is ahead by diff number of nodes.
*/
class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        int c=0; //number of times either of the pointers reach end of list
        while(pA!=null && pB!=null && c<=2){
            if(pA==pB){
                return pA;
            }
            //Increment one node at a time
            pA=pA.next; 
            pB=pB.next;
            if(pA==null){
                c++; //pA reached end
                pA=headB; //pA is reset to point to headB
            }
            if(pB==null){
                c++; //pB reached end
                pB=headA; //pB is reset to point to headA
            }
        }
        return null;
    }
}
