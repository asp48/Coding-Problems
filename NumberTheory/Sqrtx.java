/*
Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
*/

/*
Binary Search
Time: O(log x)
Space: O(1)
*/
class Solution {
    public int mySqrt(int x) {
        if(x==0){
            return 0;
        }
        int low = 1, high=x, ans=1;
        while(low <= high){
            int mid = low + (high-low)/2;
            if(x/mid == mid){
                return mid;
            } else if(mid < x/mid){
                low = mid +1;
                ans = mid;
            } else {
                high = mid-1;
            }
        }
        return ans;
    }
}