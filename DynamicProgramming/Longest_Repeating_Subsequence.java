/*
https://practice.geeksforgeeks.org/problems/longest-repeating-subsequence/0#

Given a string str, find length of the longest repeating subseequence such that the two subsequence don’t have same string character at same position, i.e., any i’th character in the two subsequences shouldn’t have the same index in the original string.
Input:
The first line of input contains an integer T denoting the number of test cases. Then T test cases follow. The first line of each test case contains an integer N denoting the length of string str.
The second line of each test case contains the string str consisting only of lower case english alphabets.

Output:
Print the length of the longest repeating subsequence for each test case in a new line.
Constraints:
1<= T <=100
1<= N <=1000

Example:
Input:
2
3
abc
5
axxxy
Output:
0
2
*/

/*
This is similar to Longest Common Subsequence with one difference. We should increase the length 
if s1[i]==s1[j] and i!=j. 
*/
class GFG {
	public static void main (String[] args) {
        Scanner s  = new Scanner(System.in);
        int t = s.nextInt();
        while(t-->0){
            int n = s.nextInt();
            String str = s.next();
            Integer[][] dp = new Integer[str.length()][str.length()];
            //int ans = lrs(str, str, str.length()-1, str.length()-1, dp);
            int ans = arrayDp(str);
            System.out.println(ans);
        }
	}
	
	public static int lrs(String s1, String s2, int i, int j, Integer[][] dp){
	    if(i==-1 || j==-1){
	        return 0;
	    }
	    if(dp[i][j]!=null){
	        return dp[i][j];
	    }
        // additional condition i!=j
	    if(s1.charAt(i)==s2.charAt(j) && i!=j){
	        dp[i][j] = 1 + lrs(s1, s2, i-1, j-1, dp);
	        return dp[i][j];
	    }
	    dp[i][j] = Math.max(lrs(s1, s2, i-1, j, dp), lrs(s1, s2, i, j-1, dp));
	    return dp[i][j];
	}
	
	public static int arrayDp(String s){
	    int n = s.length();
	    int[][] dp = new int[n+1][n+1];
	    for(int i=1;i<=n;i++){
	        for(int j=1;j<=n;j++){
	            if(s.charAt(i-1)==s.charAt(j-1) && i!=j){
	                dp[i][j] = 1 + dp[i-1][j-1];
	            } else {
	                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
	            }
	        }
	    }
	    return dp[n][n];
	}
}