class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        //return lps(s.toCharArray(), 0, n-1, new Integer[n][n]);
        return arrayDp(s.toCharArray(), n);
    }
    
    public int lps(char[] s, int i, int j, Integer[][] dp){
        if(i==j){
            return 1;
        }
        if(s[i]==s[j] && i+1==j){
            return 2;
        }
        if(dp[i][j] != null){
            return dp[i][j];
        }
        int len = 0;
        if(s[i]==s[j]){
            len = lps(s, i+1, j-1, dp)+2;
        } else {
            len = Math.max(lps(s,i+1,j, dp), lps(s,i,j-1, dp));
        }
        dp[i][j] = len;
        return len;
    }
/*
 * Time: O(n^2)
 * Space: O(n^2)
 */    
    public int arrayDp(char[] s, int n){
        int[][] dp = new int[n][n];
        for(int i=0;i<n;i++){
            dp[i][i] = 1;
            if(i<n-1){
                dp[i][i+1] = (s[i]==s[i+1])?2:1;
            }
        }
        for(int k=3;k<=n;k++){
            for(int i=0;i<=(n-k);i+=1){
                int j = i+k-1;
                if(s[i]==s[j]){
                    dp[i][j] = 2 + dp[i+1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}
