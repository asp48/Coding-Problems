/*
https://leetcode.com/problems/best-team-with-no-conflicts/

You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the highest overall score. The score of the team is the sum of scores of all the players in the team.

However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a strictly higher score than an older player. A conflict does not occur between players of the same age.

Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith player, respectively, return the highest overall score of all possible basketball teams.

 

Example 1:

Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
Output: 34
Explanation: You can choose all the players.
Example 2:

Input: scores = [4,5,6,5], ages = [2,1,2,1]
Output: 16
Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of the same age.
*/

/*
Similar to Max_Sum_Increasing_Subsequence
Time: O(N^2)
Space: O(N)
*/
class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length, max=0, ans=0;
        int[] dp = new int[n];
        int[][] team = new int[n][2];
        for(int i=0;i<n;i++)
        {
            team[i][0]=ages[i];
            team[i][1]=scores[i];
        }
        // sort in the increasing order of scores if ages are same, else sort by age.
        Arrays.sort(team, (a,b)->(a[0]==b[0]?a[1]-b[1]:a[0]-b[0]));
        // apply max_sum increasing subsequence solution to the scores.
        for(int i=0;i<n;i++){
            max = 0;
            for(int j=0;j<i;j++){
                if(team[j][1]<=team[i][1]){
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + team[i][1];
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}