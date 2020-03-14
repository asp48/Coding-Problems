/*
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]
*/
/*
At each day, the decision is to whether,
1. add today's profit to yesterday's profit (we are on the upward slope)
   Here the stocks bought during the lower peak are sold today. Note that yesterday's profit does not mean that stocks are sold yesterday itself, instead the name is just for calculation purpose. It is part of the cumulative sum we are calculating during the upward slope.
2. consider day before yesterday's profit and have a cool down yesterday (we are in a downward slope)
   Here the stocks are sold during the higher peak and no more stocks are bought, which means we are in cooldown period.

Time: O(n)
Space: O(1)
*/
class Solution {
    public int maxProfit(int[] prices) {
        int profitYesterday = 0;
        int profitDayBeforeYesterday = 0;
        for(int i=1;i<prices.length;i++){
            int profitToday = prices[i]-prices[i-1];            
            int maxProfitToday = Math.max(profitToday + profitYesterday, profitDayBeforeYesterday);
            profitDayBeforeYesterday = Math.max(profitDayBeforeYesterday, profitYesterday);
            profitYesterday = maxProfitToday;
        }
        return Math.max(profitDayBeforeYesterday, profitYesterday);
    }
}