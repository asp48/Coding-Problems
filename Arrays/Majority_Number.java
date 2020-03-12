/*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2

Boyer-Moore Voting Algorithm
time: O(n)
space: O(1)

Intuition

If we had some way of counting instances of the majority element as +1+1 and instances of any other element as -1−1, summing them would make it obvious that the majority element is indeed the majority element.

Algorithm
    Choose the first element as majority and count set to 1
    loop until the end of array
        if cur_element == majority
            increment the count
        else
            decrement the count
        if count == 0
            majority = cur_element
            count=1 //This is same as starting condition

Why does it work?
Consider an array
[7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7]
'|' -> implies the state when count becomes 0
In the first, second and third group, there are equal number of majority and non-majority elements
In the last group, there are only majority elements and count goes to 4. Ans will be 7.

Lets say count reaches 0 after scanning x elements in an array of length n.  |<---------x------>|<-------(n-x)-------->|
count of 0 implies there were equal number of majority and non-majority elements.
So count of majority element in first group of x elements = x/2
Expected count of majority element in second group of n-x elements = (total required count) - (count in first group)
                                                                   >= n/2+1 - x/2
                                                                   >= (n-x)/2 + 1
That means the majority element should still be a majority in the second group(n-x). That is why when count reaches 0, you are at the same state as when u started.
*/
class Solution {
    public int majorityElement(int[] nums) {
        int majority = nums[0];
        int count=1;
        for(int i=1;i<nums.length;i++){
            if(majority==nums[i]){
                count++;
            } else{
                count--;
                if(count==0){
                    majority = nums[i];
                    count=1;
                }
            }
        }
        return majority;
    }
}
//Compact Version of Above Solution
class Solution1 {
    public int majorityElement(int[] nums) {
        int majority = 0;
        int count=0;
        for(int num: nums){
            if(count==0){
                majority = num;
            }
            count += (num==majority)?1:-1;
        }
        return majority;
    }
}