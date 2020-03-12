/*
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

Time: O(n) 
Space: constant space
*/
/*
Store the relative difference in price at each index.
Apply kadane's algorithm to find the maximum sum.
Took 1ms faster than 74%  
The solution would be better suitable if the input itself is relative difference rather than the actual price.
*/
class Solution1 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n<2) return 0;
        int cur_sum=0, max_sum=0;
        for(int i=n-1;i>0;i--){
            prices[i]=prices[i]-prices[i-1];
            cur_sum = Math.max(prices[i], cur_sum + prices[i]);
            max_sum = Math.max(max_sum, cur_sum);
        }
        return max_sum;
    }
}
/*
The problem is to find a min and max number in the array, such that index of min < index of max.
Identify a min while traversing the array and find local optimal at each index.
Took 0ms faster than 100%
*/
class Solution2 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n<2) return 0;
        int min=Integer.MAX_VALUE;
        int profit=0;
        for(int i=0;i<n;i++){
            if(prices[i]<min){
                min=prices[i];
            }else{
                profit = Math.max(profit, prices[i]-min);
            }
        }
        return profit;
    }
}