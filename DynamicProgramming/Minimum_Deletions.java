/*
https://practice.geeksforgeeks.org/problems/minimum-deletitions/0#

Given a string of S as input. Your task is to write a program to remove or delete minimum number of characters from the string so that the resultant string is palindrome.

Note: The order of characters in the string should be maintained.

Input:
First line of input contains a single integer T which denotes the number of test cases. Then T test cases follows. First line of each test case contains a string S.

Output:
For each test case, print the deletions required to make the string palindrome.

Constraints:
1<=T<=100
1<=length(S)<=10000

Example:
Input:
2
aebcbda
geeksforgeeks
Output:
2
8
*/

class GFG {
	public static void main (String[] args) {
	    Scanner s = new Scanner(System.in);
	    int t = s.nextInt();
	    while(t-->0){
	        String str = s.next();
	        int n = str.length();
	        //int ans = helper(str.toCharArray(), 0, n-1, new Integer[n][n]);
	        int ans = arrayDp(str.toCharArray(), n);
	        System.out.println(ans);
	    }
	}
	/*
    Recursion
    Time: O(n^2)
    Space: O(n^2)
    */
	public static int helper(char[] s, int i, int j, Integer[][] dp){
	    if(i==j){
	        return 0; 
	    }
	    if(i+1==j && s[i]==s[j]){
	        return 0;
	    }
	    if(dp[i][j]!=null){
	        return dp[i][j];
	    }
	    int ans = 0;
	    if(s[i]==s[j]){
            // nothing to delete, check for deletions required to make inner substring a palindrome
	        ans = helper(s, i+1, j-1, dp);
	    } else {
            // delete first character
    	    int op1 = 1+helper(s, i+1, j, dp);
            // delete last character
    	    int op2 = 1+helper(s, i, j-1, dp);
    	    ans = Math.min(op1, op2);
	    }
	    dp[i][j] = ans;
	    return dp[i][j];
	}
	
    /*
    Array Dp
    Time: O(n^2)
    Space: O(n^2)
    */
	public static int arrayDp(char[] s, int n){
	    int[][] dp = new int[n][n];
	    for(int i=0;i<n;i++){
	        dp[i][i]=0;
	        if(i!=n-1){
	            dp[i][i+1] = (s[i]==s[i+1])?0:1;
	        }
	    }
	    for(int k=3;k<=n;k++){
	        for(int i=0;i<=n-k;i++){
	            int j=i+k-1;
	            if(s[i]==s[j]){
	                dp[i][j] = dp[i+1][j-1];
	            } else {
	                dp[i][j] = 1+Math.min(dp[i][j-1], dp[i+1][j]);
	            }
	        }
	    }
	    return dp[0][n-1];
	}
}