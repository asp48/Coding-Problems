import java.util.ArrayList;
import java.util.List;

/*
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]

Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]

Intuition:
There can be maximum of 2 numbers in the result because at max only two elments can appear more than n/3 times in an array of length n.
In the example 2, there are two elements in the result. So the elements in the input array are divided into 3 regions.
p= 1,1,1
q= 2,2,2
r= 3,3

p:q+r ~ 1:2 or q:p+r ~ 1:2
p:r ~ 2:1 or q:r ~ 2:1 or p:q ~ 1:1
Finding p in p+r and q in q+r is same as the Majority Element problem of finding an element which appears more than n/2 times in an array of length n. For example p is more than twice of r. So p will repeat more than (p+r)/2.
The solution is to convert this problem into two simpliefied Majority Element problem.

Note: A number isn't necessarily a majority just because its count is non-zero at the end, but if a number doesn't end with count of zero, it definitely can't be a majority. Therefore, we have to verify at the end, whether the generated numbers from the subproblems actually satisfy the given criteria.

time: O(n)
space; O(1)
*/
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n=nums.length;
        int m1=0, m2=1, c1=0, c2=0; //Two subproblems of finding region p,q in p,q,r.
        for(int num: nums){
            if(num==m1){
                //num belongs to region p.
                c1++;
            } else if(num==m2){
                //num belongs to region q.
                c2++;
            } else if(c1==0){
                //p is same as r
                m1=num;
                c1=1;
            } else if(c2==0){
                //q is same as r
                m2=num;
                c2=1;
            } else{ 
                //num belongs to region r.
                c1--;
                c2--;
            }
        }
        //verifying if m1 and m2 actually satisfy the criteria
        List<Integer> result = new ArrayList<>();
        c1=c2=0;
        for(int num: nums){
            if(num==m1){
                c1++;
            }else if(num==m2){
                c2++;
            }
        }
        if(c1 > n/3){
            result.add(m1);
        }
        if(c2 > n/3){
            result.add(m2);
        }
        return result; 
    }
}