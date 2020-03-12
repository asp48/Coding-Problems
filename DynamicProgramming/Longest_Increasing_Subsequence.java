import java.util.Arrays;

/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run lower than O(n2) complexity.
*/
/*
Bottom Up Recursion with Memorization
time: O(n^2)
space: O(n^2)
*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[][] memo = new int[n][n];
        int result =  getLIS(nums, -1, 0, memo);
        return result;
    }
    
    public int getLIS(int[] nums, int prevIndex, int curIndex, int[][] memo){
        if(curIndex==nums.length){
            return 0;
        }
        if(memo[prevIndex+1][curIndex]!=0){
            return memo[prevIndex+1][curIndex];
        }
        int incl_cur = 0;
        if(prevIndex < 0 || nums[curIndex]>nums[prevIndex]){
            incl_cur = 1 + getLIS(nums, curIndex, curIndex+1, memo);
        }
        int excl_cur = getLIS(nums, prevIndex, curIndex+1, memo);
        memo[prevIndex+1][curIndex] = Math.max(incl_cur, excl_cur);
        return memo[prevIndex+1][curIndex];
    }
}
/*
Top Down DP
time: O(n^2)
space: O(n^2)
*/
class Solution2 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if(n==0) return 0;
        int[][] dp = new int[n+1][n+2];
        for(int i=n-1;i>=-1;i--){
            for(int j=n-1;j>i;j--){
                if(i==-1 || nums[j]>nums[i]){
                    dp[i+1][j] = 1 + dp[j+1][j+1];
                }
                dp[i+1][j] = Math.max(dp[i+1][j], dp[i+1][j+1]);
            }
        }
        return dp[0][0];
    }
}
/*
Input: [10,9,2,5,3,7,101,18]
DP Matrix: row(prevIndex), column(currentIndex)
4 4 4 3 3 2 1 1 
0 1 1 1 1 1 1 1 
0 0 1 1 1 1 1 1 
0 0 0 3 3 2 1 1 
0 0 0 0 2 2 1 1 
0 0 0 0 0 2 1 1 
0 0 0 0 0 0 1 1 
0 0 0 0 0 0 0 0 
0 0 0 0 0 0 0 0 
Output: dp[0][0] or 4
*/

/*
Bottom-Up DP
dp[i] stores the length of LIS that ends with ith element
If we know max length of LIS upto ith index, we can figure out the max length of LIS possible upto (i+1), by choosing the best previous element with index j, such that array[j] is smaller than array[i+1] and dp[j] has the max LIS between dp[0] to dp[i] inclusively. 
The value stored in dp array is local maximum and required answer is the maximum element in the dp array.

time:O(n^2)
space: O(n)
*/
class Solution3 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if(n==0) return 0;
        int[] dp = new int[n+1];
        int ans = 0;
        // Iterating to find max LIS upto ith index
        for(int i=0;i<n;i++){
            int maxVal = 0;
            // Choosing the best previous element
            for(int j=0;j<i;j++){
                // such that nums[j]<nums[i] and it is the maximum between dp[0] to dp[i-1] inclusively.
                if(nums[j]<nums[i] && dp[j] > maxVal){
                    maxVal = dp[j];
                }
            }
            // store the max LIS possible by including ith element
            dp[i] = maxVal + 1;
            // find the global maximum
            ans = Math.max(dp[i], ans);
        }

        //(Optional) If it is required to construct one of the LIS
        int[] result = new int[ans];
        int len = ans;
        for(int i=n-1;i>=0;i--){
            if(dp[i]==len && (len==ans || dp[i]<result[len])){
                result[len-1] = nums[i];
                len--;
            }
        }

        // return the global maximum
        return ans;
    }
}
/*
Binary Search

Intuition:
The starting idea for the algorithm is that we will scan the input from left to right, maintaining at any given time a representation of all the possible increasing subsequences that can be formed with the elements seen so far, such that the sub-sequences we track are ones that could contribute to the final solution. We will aggressively discard subsequences that can never influence the solution, and this will allow the algorithm to become efficient.
Ex: 
Array dp is used to store all the LIS discoverd so far which could contribute to final solution.
case 1: Appending at the end, when current element is greater than largest element in the LIS discovered so far.
case 2; Replacing an element slightly greater than the current element, when current element is smaller than the largest element in the LIS discovered so far
Input: 5, 8, 11, 7, 15, 12, 13
Iterate:
At i=0, 5                  // case 1, 5 > none
At i=1, 5, 8               // case 1, 8 > 5
At i=2, 5, 8, 11           // case 1, 11 > 8
At i=3, 5, 7, 11           // case 2, 7 < 11 and 8 is slighly greater than 7
At i=4, 5, 7, 11, 15       // case 1, 15 > 11
At i=5, 5, 7, 11, 12       // case 2, 12 < 15 and 15 is slightly greater than 12
At i=6, 5, 7, 11, 12, 13   // case 1, 12 > 12 Note that we could extended the length only because 15 was replaced by 12.

What does case 2 imply?
Consider two Increasing Subsequence as below
3,4,8
3,4,6
Now, we have two LIS to track. But if we closely observe, 3,4,6 has more potential to extend into a larger LIS than 3,4,8.
Because lets say next element is 10
Both the list can be extended. 3,4,8,10 and 3,4,6,10
Now, what if next element is 7 instead of 10
Only one of the list can be extended. i.e 3,4,6,7
So it makes sense to track and store only 3,4,6 and discard 3,4,8.

References;
https://www.quora.com/Why-and-how-does-the-O-n-log-n-longest-increasing-subsequence-algorithm-using-a-binary-search-work
http://lightoj.com/article_show.php?article=1000&language=english&type=pdf
https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/

Algorithm:
1. Initialize dp array of size n+1 with zeroes.
2. Initialize len=0;
3. For each element: num in the given array
    3.1 Apply binary search on dp(low-included = 0, high-excluded = len, key=num) to get the insertion point i. Insertion point is an index in dp where all the elements before it are less than current element num. Here BS works because dp array contains increasing subsequence and hence always remains sorted.
    3.2 Insert num at index i in dp array.
    3.3 If i==len, it implies current element is appended at the end of the sequence and no replace performed. So increment the length.
4 Return len as the required answer.  


Note: The sequence stored in dp array may not be valid LIS always, but length will always be valid and is the maximum length possible upto ith index. This can be understood if you observe that length never decreases even after including current element. Current element either replaces some element in the generated sequence or appends to the end of the sequence. So length of the sequence upto i will increase only if array[i] is greater than all the elements in the sequence generated so far. However the solution can be modified as in Solution5 to get the valid LIS.
Ex:
Input: [10,9,2,5,3,7,101,1]
Flow:
Index returned by Binary Search : dp array
-1: 10 
-1: 9 
-1: 2 
-2: 2 5 
-2: 2 3 
-3: 2 3 7 
-4: 2 3 7 101 
-1: 1 3 7 101 // Though 1 last element, it replaced 2 in the previous sequence. This sequence is invalid.
Ouput: 4

Arrays.binarySearch(array, from, to, key)
from-> low included, to->hight excluded, key-> value to be searched
Returns: index of the search key, if it is contained in the array within the specified range; otherwise, (-(insertion point) - 1). The insertion point is defined as the point at which the key would be inserted into the array: the index of the first element in the range greater than the key, or toIndex if all elements in the range are less than the specified key. Note that this guarantees that the return value will be >= 0 if and only if the key is found.

time: O(nlogn)
space: O(n)
*/
class Solution4 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // Step 1
        int[] dp = new int[n];
        // Step 2
        int len = 0, i=0;
        // Step 3
        for(int num: nums){
            // Step 3.1
            i = Arrays.binarySearch(dp, 0, len, num);
            if(i<0){
               i = -(i+1);
            }
            // Step 3.2
            dp[i] = num;
            // Step 3.3
            if(i==len){
                len++;
            }
        }
        // Step 4
        return len;
    }
}
// Using the above logic, to print one of the LIS.
class Solution5 {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        //Stores the length of LIS that ends with ith element. Similar to dp array in Solution3.
        int[] L = new int[n];
        int len = 0, insertPoint=0;
        for(int i=0; i<n; i++){
            insertPoint = Arrays.binarySearch(dp, 0, len, nums[i]);
            if(insertPoint<0){
               insertPoint = -(insertPoint+1);
            }
            dp[insertPoint] = nums[i];
            // Store the length of LIS that ends with nums[i]
            L[i] = insertPoint+1;
            if(insertPoint==len){
                len++;
            }
        }
        int ans = len;
        // L can be used similar to dp array in Solution3 to print one of the LIS.
        int[] result = new int[len];
        for(int i=n-1;i>=0;i--){
            if(L[i]==len && (len==ans || L[i]<result[len])){
                result[len-1] = nums[i];
                len--;
            }
        }
        for(int i=0;i<ans;i++){
            System.out.print(result[i] + " ");
        }
        return ans;
    }
}