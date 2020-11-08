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

/*
Explaination:
https://www.youtube.com/watch?v=N-zlfQJWbYE
Other approaches
*/
class Solution {
    public int maxProfit(int[] prices) {
        //return getProfit(prices, true, 0);
        return arrayDp(prices);
    }
    
    public int getProfit(int[] prices, boolean buy, int i){
        if(i>=prices.length){
            return 0;
        }
        if(buy){
            int buy_now = getProfit(prices, false, i+1)-prices[i];
            int wait_today = getProfit(prices, true, i+1);
            return Math.max(buy_now, wait_today);
        } else {
            int sell_today = getProfit(prices, true, i+2)+prices[i];
            int hold_today = getProfit(prices, false, i+1);
            return Math.max(sell_today, hold_today);
        }
    }
    
    public int arrayDp(int[] prices){
        int[][] dp = new int[prices.length][2];
        if(prices.length<=1){
            return 0;
        }
        if(prices.length==2){
            return Math.max(prices[1]-prices[0], 0);
        }
        dp[0][0] = -prices[0];
        dp[1][0] = Math.max(-prices[0], -prices[1]);
        dp[0][1] = 0;
        dp[1][1] = Math.max(0, prices[1]-prices[0]);
        for(int i=2;i<prices.length;i++){
            dp[i][0] = Math.max(dp[i-2][1]-prices[i], dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][0]+prices[i], dp[i-1][1]);
        }
        return dp[prices.length-1][1];
    }
}